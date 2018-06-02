package nl.timgoes.dbservice.dbservicemysql.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name="USER_CREDIT")
public class UserCredit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="CREDIT_ID")
    private Credit credit;

    @Column(name = "AMOUNT", columnDefinition = "DECIMAL(26,2)")
    private BigDecimal amount;

    public UserCredit(User user, Credit credit, BigDecimal amount) {
        this.user = user;
        this.credit = credit;
        this.amount = amount;
    }

    public UserCredit() {
   }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserCredit{" +
                "id=" + id +
                ", user=" + user +
                ", credit=" + credit +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCredit)) return false;
        UserCredit that = (UserCredit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(credit, that.credit) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, credit, amount);
    }
}
