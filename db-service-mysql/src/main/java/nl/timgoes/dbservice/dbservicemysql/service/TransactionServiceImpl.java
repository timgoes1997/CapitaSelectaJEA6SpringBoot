package nl.timgoes.dbservice.dbservicemysql.service;

import nl.timgoes.dbservice.dbservicemysql.model.*;
import nl.timgoes.dbservice.dbservicemysql.repository.TransactionRepository;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.CreditService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.TransactionService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserCreditService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserService;
import nl.timgoes.exceptionhandling.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CreditService creditService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreditService userCreditService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction findById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        }
        throw new TransactionNotFoundException("ID: " + id.toString());
    }

    @Override
    public Transaction createGiftTransaction(User creator, Credit creditGiven, BigDecimal amountGiven, User receiver) {
        UserCredit creatorCredit = userCreditService.findByCreditAndUser(creditGiven, creator);

        if(creatorCredit ==  null){
            throw new UserCreditNotFoundException("User has no credits of given type");
        }

        if (creatorCredit.getAmount().compareTo(amountGiven) < 0) {
            throw new NotEnoughUserCreditException("Your credit is to low by " + creatorCredit.getAmount().subtract(amountGiven).toString());
        }
        return transactionRepository.save(new Transaction(creator, TransactionStatus.INPROGRESS, creditGiven, amountGiven, receiver));
    }

    @Override
    public Transaction createGiftTransaction(String creatorName, String receiverCreditGivenName, BigDecimal receiverAmountGiven, String receiverName) {
        return createGiftTransaction(
                userService.findByUsername(creatorName),
                creditService.findByName(receiverCreditGivenName),
                receiverAmountGiven,
                userService.findByUsername(receiverName));
    }

    @Override
    public Transaction createStorageTransaction(String creatorName, String creatorCreditReceived, BigDecimal creatorReceivedAmount) {
        return createStorageTransaction(
                userService.findByUsername(creatorName),
                creditService.findByName(creatorCreditReceived),
                creatorReceivedAmount);
    }

    @Override
    public Transaction createExchangeTransaction(String creatorName, String creatorCreditReceivedName, BigDecimal creatorReceivedAmount, String receiverName, String receiverCreditGivenName, BigDecimal receiverAmountGiven) {
        return createExchangeTransaction(
                userService.findByUsername(creatorName),
                creditService.findByName(creatorCreditReceivedName),
                creatorReceivedAmount,
                userService.findByUsername(receiverName),
                creditService.findByName(creatorCreditReceivedName),
                receiverAmountGiven);
    }

    @Override
    public Transaction createStorageTransaction(User creator, Credit creatorCreditReceived, BigDecimal creatorReceivedAmount) {
        return transactionRepository.save(new Transaction(creator, TransactionStatus.INPROGRESS, creatorCreditReceived, creatorReceivedAmount));
    }

    @Override
    public Transaction createExchangeTransaction(User creator, Credit creatorCreditReceived, BigDecimal creatorReceivedAmount, User receiver, Credit receiverCreditGiven, BigDecimal receiverAmountGiven) {
        UserCredit creatorCredit = userCreditService.findByCreditAndUser(receiverCreditGiven, creator);

        if(creatorCredit ==  null){
            throw new UserCreditNotFoundException("User has no credits of given type");
        }

        if (creatorCredit.getAmount().compareTo(receiverAmountGiven) < 0) {
            throw new NotEnoughUserCreditException("Your credit is to low by " + creatorCredit.getAmount().subtract(receiverAmountGiven).toString());
        }
        return transactionRepository.save(new Transaction(creator, TransactionStatus.INPROGRESS, creatorCreditReceived, creatorReceivedAmount, receiverCreditGiven, receiverAmountGiven, receiver));
    }

    @Override
    public Transaction acceptTransaction(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (!optionalTransaction.isPresent()) {
            throw new TransactionNotFoundException("Not found for ID: " + id.toString());
        }

        Transaction transaction = optionalTransaction.get();
        if (transaction.getStatus() != TransactionStatus.INPROGRESS) {
            throw new TransactionAlreadyCompletedException("Transaction already has the following status: " + transaction.getStatus().name());
        }

        switch (transaction.getType()) {
            case GIFT:
                return acceptGiftTransaction(transaction);
            case STORAGE:
                return acceptStorageTransaction(transaction);
            case EXCHANGE:
                return acceptExchangeTransaction(transaction);
        }
        throw new WrongTransactionException("Given transaction type was null");
    }

    @Override
    public Transaction acceptStorageTransaction(Transaction transaction) {
        if (transaction.getType() != TransactionType.STORAGE) {
            throw new WrongTransactionException("Tried to accept " + transaction.getType().name() + " but needed " + TransactionType.STORAGE.name());
        }

        userCreditService.addToUserCredit(
                transaction.getCreator(),
                transaction.getCreditToReceive(),
                transaction.getAmountToReceive());

        transaction.setStatus(TransactionStatus.COMPLETE);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction acceptGiftTransaction(Transaction transaction) {
        if (transaction.getType() != TransactionType.GIFT) {
            throw new WrongTransactionException("Tried to accept " + transaction.getType().name() + " but needed " + TransactionType.GIFT.name());
        }

        UserCredit creatorCreditGiven = userCreditService.hasEnoughCreditForTransaction(
                transaction.getCreator(),
                transaction.getCreditToGive(),
                transaction.getAmountToGive());

        //Transfering money from creator to receiver
        userCreditService.transfer(
                creatorCreditGiven,
                transaction.getReceiver(),
                transaction.getAmountToGive());

        transaction.setStatus(TransactionStatus.COMPLETE);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction acceptExchangeTransaction(Transaction transaction) {
        if (transaction.getType() != TransactionType.EXCHANGE) {
            throw new WrongTransactionException("Tried to accept " + transaction.getType().name() + " but needed " + TransactionType.EXCHANGE.name());
        }

        UserCredit creatorCreditGiven = userCreditService.hasEnoughCreditForTransaction(
                transaction.getCreator(),
                transaction.getCreditToGive(),
                transaction.getAmountToGive());

        //check receiverCredit
        UserCredit receiverCreditGiven = userCreditService.hasEnoughCreditForTransaction(
                transaction.getReceiver(),
                transaction.getCreditToReceive(),
                transaction.getAmountToReceive());


        //Transfering money from creator to receiver
        userCreditService.transfer(
                creatorCreditGiven,
                transaction.getReceiver(),
                transaction.getAmountToGive());


        //Transferingg money from receiver to updater
        userCreditService.transfer(
                receiverCreditGiven,
                transaction.getCreator(),
                transaction.getAmountToReceive());

        transaction.setStatus(TransactionStatus.COMPLETE);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction denyTransaction(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (!optionalTransaction.isPresent()) {
            throw new TransactionNotFoundException("Not found for ID: " + id.toString());
        }

        Transaction transaction = optionalTransaction.get();
        if (transaction.getStatus() != TransactionStatus.INPROGRESS) {
            throw new TransactionAlreadyCompletedException("Transaction already has the following status: " + transaction.getStatus().name());
        }

        transaction.setStatus(TransactionStatus.CANCELED);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> findTransactionCreator(User user) {
        return transactionRepository.findByCreatorOrderByTransactionDate(user);
    }

    @Override
    public List<Transaction> findTransactionCreator(String userName) {
        return findTransactionCreator(userService.findByUsername(userName));
    }

    @Override
    public List<Transaction> findTransactionReceiver(User user) {
        return transactionRepository.findByReceiverOrderByTransactionDate(user);
    }

    @Override
    public List<Transaction> findTransactionReceiver(String userName) {
        return findTransactionReceiver(userService.findByUsername(userName));
    }

    @Override
    public List<Transaction> findTransactionByStatusAndCreator(User user, TransactionStatus status) {
        return transactionRepository.findByCreatorAndStatus(user, status);
    }

    @Override
    public List<Transaction> findTransactionByStatusAndCreator(String userName, TransactionStatus status) {
        return findTransactionByStatusAndCreator(userService.findByUsername(userName), status);
    }

    @Override
    public List<Transaction> findTransactionByStatusAndReceiver(User user, TransactionStatus status) {
        return transactionRepository.findByReceiverAndStatus(user, status);
    }

    @Override
    public List<Transaction> findTransactionByStatusAndReceiver(String userName, TransactionStatus status) {
        return findTransactionByStatusAndReceiver(userService.findByUsername(userName), status);
    }


}
