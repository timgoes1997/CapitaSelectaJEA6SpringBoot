package nl.timgoes.dbservice.dbservicemysql.service.interfaces;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;
import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.model.UserCredit;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.List;

public interface UserCreditService {
    List<UserCredit> findByCredit(Credit credit);
    List<UserCredit> findByCredit(String creditName);
    List<UserCredit> findByUser(User user);
    List<UserCredit> findByUser(String userName);
    UserCredit findByCreditAndUser(Credit credit, User user);
    UserCredit findByCreditAndUser(String creditName, String userName);

    UserCredit createUserCredit(User user, Credit credit, BigDecimal amount);
    UserCredit createUserCredit(String creditName, String userName, BigDecimal amount);
    UserCredit updateUserCredit(User user, Credit credit, BigDecimal amount);
    UserCredit updateUserCredit(String creditName, String userName, BigDecimal amount);
}
