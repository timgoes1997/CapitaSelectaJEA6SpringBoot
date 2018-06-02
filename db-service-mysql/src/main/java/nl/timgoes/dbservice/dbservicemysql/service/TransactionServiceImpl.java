package nl.timgoes.dbservice.dbservicemysql.service;

import nl.timgoes.dbservice.dbservicemysql.model.Transaction;
import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{


    @Override
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public Transaction createGiftTransaction() {
        return null;
    }

    @Override
    public Transaction createStorageTransaction() {
        return null;
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
    public List<Transaction> findTransactionFromUser(User user) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionToUser(User user) {
        return null;
    }
}
