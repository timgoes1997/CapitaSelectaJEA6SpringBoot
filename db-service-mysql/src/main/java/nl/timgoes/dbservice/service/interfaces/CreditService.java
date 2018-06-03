package nl.timgoes.dbservice.service.interfaces;

import nl.timgoes.core.model.*;

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
