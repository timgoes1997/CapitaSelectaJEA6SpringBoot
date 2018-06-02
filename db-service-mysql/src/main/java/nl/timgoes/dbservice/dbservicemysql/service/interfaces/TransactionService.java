package nl.timgoes.dbservice.dbservicemysql.service.interfaces;

import nl.timgoes.dbservice.dbservicemysql.model.Transaction;
import nl.timgoes.dbservice.dbservicemysql.model.User;

import java.util.List;

public interface TransactionService {
    Transaction findById(Long id);
    Transaction createGiftTransaction();
    Transaction createStorageTransaction();

    Transaction acceptTransaction(Long id);
    Transaction denyTransaction(Long id);

    List<Transaction> findTransactionFromUser(User user);
    List<Transaction> findTransactionToUser(User user);
}
