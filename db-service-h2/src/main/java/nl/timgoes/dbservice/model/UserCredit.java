package nl.timgoes.dbservice.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="USER_CREDIT")
public class UserCredit {

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "CREDIT")
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
}
