package nl.timgoes.dbservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "USER")
public class User {

    @Column(name = "NAME")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date registrationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserCredit> credits;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.PERSIST)
    private List<Transaction> createdTransactions;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.PERSIST)
    private List<Transaction> receivedTranstactions;

    public User(String name, Date registrationDate, List<UserCredit> credits, List<Transaction> createdTransactions, List<Transaction> receivedTranstactions) {
        this.name = name;
        this.registrationDate = registrationDate;
        this.credits = credits;
        this.createdTransactions = createdTransactions;
        this.receivedTranstactions = receivedTranstactions;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<UserCredit> getCredits() {
        return credits;
    }

    public void setCredits(List<UserCredit> credits) {
        this.credits = credits;
    }

    public List<Transaction> getCreatedTransactions() {
        return createdTransactions;
    }

    public void setCreatedTransactions(List<Transaction> createdTransactions) {
        this.createdTransactions = createdTransactions;
    }

    public List<Transaction> getReceivedTranstactions() {
        return receivedTranstactions;
    }

    public void setReceivedTranstactions(List<Transaction> receivedTranstactions) {
        this.receivedTranstactions = receivedTranstactions;
    }
}
