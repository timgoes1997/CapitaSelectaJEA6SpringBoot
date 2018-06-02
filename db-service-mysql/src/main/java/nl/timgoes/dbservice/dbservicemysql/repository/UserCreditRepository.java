package nl.timgoes.dbservice.dbservicemysql.repository;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;
import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.model.UserCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCreditRepository extends JpaRepository<UserCredit, Long> {
    List<UserCredit> findAllByOrderByLastUpdateDesc();
    List<UserCredit> findAllByUser(User user);
    List<UserCredit> findAllByUserOrderByLastUpdateDesc(User user);
    UserCredit findByUserAndCredit(User user, Credit credit);
    List<UserCredit> findAllByCredit(Credit credit);
}
