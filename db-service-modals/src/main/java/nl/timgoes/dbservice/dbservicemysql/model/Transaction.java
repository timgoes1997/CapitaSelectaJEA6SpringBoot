package nl.timgoes.dbservice.dbservicemysql.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name="TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

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

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", creator=" + creator +
                ", status=" + status +
                ", amount=" + amount +
                ", receiver=" + receiver +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(creator, that.creator) &&
                status == that.status &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, creator, status, amount, receiver);
    }
}
