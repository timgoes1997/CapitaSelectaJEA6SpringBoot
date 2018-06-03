package nl.timgoes.dbservice.modals.repository;

import nl.timgoes.dbservice.modals.model.Credit;
import nl.timgoes.dbservice.modals.model.User;
import nl.timgoes.dbservice.modals.model.UserCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCreditRepository extends JpaRepository<UserCredit, Long> {
    List<UserCredit> findAllByOrderByLastUpdateDesc();
    List<UserCredit> findAllByUser(User user);
    List<UserCredit> findAllByUserOrderByLastUpdateDesc(User user);
    UserCredit findByUserAndCredit(User user, Credit credit);
    List<UserCredit> findAllByCredit(Credit credit);
}
