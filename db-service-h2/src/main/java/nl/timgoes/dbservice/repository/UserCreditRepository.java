package nl.timgoes.dbservice.repository;

import nl.timgoes.core.model.Credit;
import nl.timgoes.core.model.User;
import nl.timgoes.core.model.UserCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCreditRepository extends JpaRepository<UserCredit, Long> {
    List<UserCredit> findAllByOrderByLastUpdateDesc();
    List<UserCredit> findAllByUser(User user);
    List<UserCredit> findAllByUserOrderByLastUpdateDesc(User user);
    UserCredit findByUserAndCredit(User user, Credit credit);
    List<UserCredit> findAllByCredit(Credit credit);
}
