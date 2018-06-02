package nl.timgoes.dbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="CREDIT")
public class Credit {

    @Column(name="NAME")
    private String Name;

    @Column(name = "AMOUNT", columnDefinition = "DECIMAL(26,13)")
    private BigDecimal value;

    @Temporal(TemporalType.DATE)
    @Column(name="CREATION_DATE")
    private Date creationDate;

    public Credit(String name, BigDecimal value, Date creationDate) {
        Name = name;
        this.value = value;
        this.creationDate = creationDate;
    }

    public Credit() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
