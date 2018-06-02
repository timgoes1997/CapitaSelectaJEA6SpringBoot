package nl.timgoes.dbservice.dbservicemysql.service;

import nl.timgoes.dbservice.dbservicemysql.model.*;
import nl.timgoes.dbservice.dbservicemysql.repository.TransactionRepository;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.CreditService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.TransactionService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserCreditService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserService;
import nl.timgoes.exceptionhandling.exceptions.NotEnoughUserCreditException;
import nl.timgoes.exceptionhandling.exceptions.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

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
        if(transaction.isPresent()){
            return transaction.get();
        }
        throw new TransactionNotFoundException("ID: " + id.toString());
    }

    @Override
    public Transaction createGiftTransaction(User creator, Credit creditGiven, BigDecimal amountGiven, User receiver) {
        UserCredit creatorCredit = userCreditService.findByCreditAndUser(creditGiven,creator);
        if(creatorCredit.getAmount().compareTo(amountGiven) < 0) {
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
        if(creatorCredit.getAmount().compareTo(receiverAmountGiven) < 0) {
            throw new NotEnoughUserCreditException("Your credit is to low by " + creatorCredit.getAmount().subtract(receiverAmountGiven).toString());
        }
        return transactionRepository.save(new Transaction(creator, TransactionStatus.INPROGRESS, creatorCreditReceived, creatorReceivedAmount, receiverCreditGiven, receiverAmountGiven, receiver));
    }

    @Override
    public Transaction acceptTransaction(Long id) {
        return null;
    }

    @Override
    public Transaction denyTransaction(Long id) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionCreator(User user) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionCreator(String userName) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionReceiver(User user) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionReceiver(String userName) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionByStatusAndCreator(User user, TransactionStatus status) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionByStatusAndCreator(String userName, TransactionStatus status) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionByStatusAndReceiver(User user, TransactionStatus status) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionByStatusAndReceiver(String userName, TransactionStatus status) {
        return null;
    }


}
