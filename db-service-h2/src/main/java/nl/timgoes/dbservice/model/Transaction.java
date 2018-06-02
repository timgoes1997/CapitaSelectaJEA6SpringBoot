package nl.timgoes.dbservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity(name="TRANSACTION")
public class Transaction {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CREATOR_ID")
    private User creator;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TransactionStatus status;

    @Column(name = "AMOUNT", columnDefinition = "DECIMAL(26,2)")
    private BigDecimal amount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RECEIVER_ID")
    private User receiver;

    public Transaction(User creator, TransactionStatus status, BigDecimal amount, User receiver) {
        this.creator = creator;
        this.status = status;
        this.amount = amount;
        this.receiver = receiver;
    }

    public Transaction() {
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
