package nl.timgoes.dbservice.repository;

import nl.timgoes.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String username);
}
