package nl.timgoes.dbservice.dbservicemysql.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name="TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="CREATOR_ID")
    private User creator;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private TransactionType type;

    @Temporal(TemporalType.DATE)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="CREDIT_RECEIVE")
    private Credit creditToReceive;

    @Column(name = "AMOUNT_RECEIVE", columnDefinition = "DECIMAL(26,13)")
    private BigDecimal amountToReceive;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="CREDIT_GIVE")
    private Credit creditToGive;

    @Column(name = "AMOUNT_GIVE", columnDefinition = "DECIMAL(26,13)")
    private BigDecimal amountToGive;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="RECEIVER_ID")
    private User receiver;

    public Transaction(User creator, TransactionStatus status, Credit creditToReceive, BigDecimal amountToReceive) {
        this.creator = creator;
        this.status = status;
        this.creditToReceive = creditToReceive;
        this.amountToReceive = amountToReceive;
        this.type = TransactionType.STORAGE;
    }

    public Transaction(User creator, TransactionStatus status, Credit creditToGive, BigDecimal amountToGive, User receiver) {
        this.creator = creator;
        this.status = status;
        this.creditToGive = creditToGive;
        this.amountToGive = amountToGive;
        this.receiver = receiver;
        this.type = TransactionType.GIFT;
    }

    public Transaction(User creator, TransactionStatus status, Credit creditToReceive, BigDecimal amountToReceive, Credit creditToGive, BigDecimal amountToGive, User receiver) {
        this.creator = creator;
        this.status = status;
        this.creditToReceive = creditToReceive;
        this.amountToReceive = amountToReceive;
        this.creditToGive = creditToGive;
        this.amountToGive = amountToGive;
        this.receiver = receiver;
        this.type = TransactionType.EXCHANGE;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Credit getCreditToReceive() {
        return creditToReceive;
    }

    public void setCreditToReceive(Credit creditToReceive) {
        this.creditToReceive = creditToReceive;
    }

    public BigDecimal getAmountToReceive() {
        return amountToReceive;
    }

    public void setAmountToReceive(BigDecimal amountToReceive) {
        this.amountToReceive = amountToReceive;
    }

    public Credit getCreditToGive() {
        return creditToGive;
    }

    public void setCreditToGive(Credit creditToGive) {
        this.creditToGive = creditToGive;
    }

    public BigDecimal getAmountToGive() {
        return amountToGive;
    }

    public void setAmountToGive(BigDecimal amountToGive) {
        this.amountToGive = amountToGive;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(creator, that.creator) &&
                status == that.status &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(creditToReceive, that.creditToReceive) &&
                Objects.equals(amountToReceive, that.amountToReceive) &&
                Objects.equals(creditToGive, that.creditToGive) &&
                Objects.equals(amountToGive, that.amountToGive) &&
                Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, creator, status, transactionDate, creditToReceive, amountToReceive, creditToGive, amountToGive, receiver);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", creator=" + creator +
                ", status=" + status +
                ", transactionDate=" + transactionDate +
                ", creditToReceive=" + creditToReceive +
                ", amountToReceive=" + amountToReceive +
                ", creditToGive=" + creditToGive +
                ", amountToGive=" + amountToGive +
                ", receiver=" + receiver +
                '}';
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
