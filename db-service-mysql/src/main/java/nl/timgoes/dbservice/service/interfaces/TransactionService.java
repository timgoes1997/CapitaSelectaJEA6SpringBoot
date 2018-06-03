package nl.timgoes.dbservice.service.interfaces;

import nl.timgoes.core.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction findById(Long id);
    Transaction createGiftTransaction(User creator,
                                      Credit receiverCreditGiven,
                                      BigDecimal receiverAmountGiven,
                                      User receiver);
    Transaction createGiftTransaction(String creatorName,
                                      String receiverCreditGivenName,
                                      BigDecimal receiverAmountGiven,
                                      String receiverName);
    Transaction createStorageTransaction(User creator,
                                         Credit creatorCreditReceived,
                                         BigDecimal creatorReceivedAmount);
    Transaction createStorageTransaction(String creatorName,
                                         String creatorCreditReceived,
                                         BigDecimal creatorReceivedAmount);
    Transaction createExchangeTransaction(User creator,
                                          Credit creatorCreditReceived,
                                          BigDecimal creatorReceivedAmount,
                                          User receiver,
                                          Credit receiverCreditGiven,
                                          BigDecimal receiverAmountGiven);
    Transaction createExchangeTransaction(String creatorName,
                                          String creatorCreditReceivedName,
                                          BigDecimal creatorReceivedAmount,
                                          String receiverName,
                                          String receiverCreditGivenName,
                                          BigDecimal receiverAmountGiven);

    Transaction acceptTransaction(Long id);
    Transaction acceptStorageTransaction(Transaction transaction);
    Transaction acceptGiftTransaction(Transaction transaction);
    Transaction acceptExchangeTransaction(Transaction transaction);

    Transaction denyTransaction(Long id);

    List<Transaction> findTransactionCreator(User user);
    List<Transaction> findTransactionCreator(String userName);
    List<Transaction> findTransactionReceiver(User user);
    List<Transaction> findTransactionReceiver(String userName);

    List<Transaction> findTransactionByStatusAndCreator(User user, TransactionStatus status);
    List<Transaction> findTransactionByStatusAndCreator(String userName, TransactionStatus status);
    List<Transaction> findTransactionByStatusAndReceiver(User user, TransactionStatus status);
    List<Transaction> findTransactionByStatusAndReceiver(String userName, TransactionStatus status);
    List<Transaction> findTransactionByStatus(User user, TransactionStatus status);
    List<Transaction> findTransactionByStatus(String userName, TransactionStatus status);
}
