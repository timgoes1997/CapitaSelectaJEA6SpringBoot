package nl.timgoes.dbservice.modals.repository;

import nl.timgoes.dbservice.modals.model.Transaction;
import nl.timgoes.dbservice.modals.model.TransactionStatus;
import nl.timgoes.dbservice.modals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByReceiverOrderByTransactionDate(User receiver);
    List<Transaction> findByReceiverOrderByStatus(User receiver);
    List<Transaction> findByReceiverAndStatus(User receiver, TransactionStatus status);

    List<Transaction> findByCreatorOrderByTransactionDate(User creator);
    List<Transaction> findByCreatorOrderByStatus(User creator);
    List<Transaction> findByCreatorAndStatus(User receiver, TransactionStatus status);

    List<Transaction> findByStatusAndCreatorOrReceiverOrderByTransactionDateDesc(User creator, User receiver, TransactionStatus status);
}
