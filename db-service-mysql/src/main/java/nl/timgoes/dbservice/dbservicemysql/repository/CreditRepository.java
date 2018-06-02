package nl.timgoes.dbservice.dbservicemysql.repository;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByName(String name);
    List<Credit> findByCreationDateBefore(Date date);
}
