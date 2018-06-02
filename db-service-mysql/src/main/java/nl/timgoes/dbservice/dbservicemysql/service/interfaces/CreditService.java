package nl.timgoes.dbservice.dbservicemysql.service.interfaces;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;

import java.math.BigDecimal;

public interface CreditService {
    Credit findByName(String name);
    Credit createCredit(String name, BigDecimal value);
    Credit changeValue(String name, BigDecimal value);
    void deleteCredit(String name);
    void deleteCredit(Long id);
}
