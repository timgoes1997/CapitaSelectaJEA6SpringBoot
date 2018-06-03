package nl.timgoes.dbservice.dbservicemysql.service.interfaces;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;

import java.math.BigDecimal;
import java.util.List;

public interface CreditService {
    Credit findByName(String name);
    Credit createCredit(String name, BigDecimal value);
    Credit changeValue(String name, BigDecimal value);
    List<Credit> getCredits();
    List<String> getCreditNames();
    void deleteCredit(String name);
    void deleteCredit(Long id);
}
