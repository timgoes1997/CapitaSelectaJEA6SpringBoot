package nl.timgoes.dbservice.dbservicemysql.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name="CREDIT")
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

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

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", value=" + value +
                ", creationDate=" + creationDate +
                '}';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit)) return false;
        Credit credit = (Credit) o;
        return Objects.equals(id, credit.id) &&
                Objects.equals(Name, credit.Name) &&
                Objects.equals(value, credit.value) &&
                Objects.equals(creationDate, credit.creationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, Name, value, creationDate);
    }
}
