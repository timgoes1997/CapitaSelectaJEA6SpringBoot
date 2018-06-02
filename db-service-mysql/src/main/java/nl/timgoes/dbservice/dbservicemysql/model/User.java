package nl.timgoes.dbservice.dbservicemysql.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

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

    public User(String name, Date registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public User() {
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", credits=" + credits +
                ", createdTransactions=" + createdTransactions +
                ", receivedTranstactions=" + receivedTranstactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(credits, user.credits) &&
                Objects.equals(createdTransactions, user.createdTransactions) &&
                Objects.equals(receivedTranstactions, user.receivedTranstactions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, registrationDate, credits, createdTransactions, receivedTranstactions);
    }
}
