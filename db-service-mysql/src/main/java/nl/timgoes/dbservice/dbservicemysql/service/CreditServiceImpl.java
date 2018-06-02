package nl.timgoes.dbservice.dbservicemysql.service;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;
import nl.timgoes.dbservice.dbservicemysql.repository.CreditRepository;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.CreditService;
import nl.timgoes.exceptionhandling.exceptions.CreditNameAlreadyExistsException;
import nl.timgoes.exceptionhandling.exceptions.CreditNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Credit findByName(String name) {
        List<Credit> creditList = creditRepository.findByName(name);
        if (creditList.size() < 1) throw new CreditNotFoundException("name: " + name);
        return creditList.get(0);
    }

    @Override
    public Credit createCredit(String name, BigDecimal value) {
        if(creditRepository.findByName(name).size() > 0){
            throw new CreditNameAlreadyExistsException("name: " + name);
        }
        return creditRepository.save(new Credit(name, value, new Date()));
    }

    @Override
    public Credit changeValue(String name, BigDecimal value) {
        List<Credit> creditList = creditRepository.findByName(name);
        if (creditList.size() < 1) throw new CreditNotFoundException("name: " + name);
        Credit credit = creditList.get(0);
        credit.setValue(value);
        return creditRepository.save(credit);
    }

    @Override
    public void deleteCredit(String name) {
        List<Credit> creditList = creditRepository.findByName(name);
        if (creditList.size() < 1) throw new CreditNotFoundException("name: " + name);
        Credit credit = creditList.get(0);
        creditRepository.delete(credit);
    }

    @Override
    public void deleteCredit(Long id) {
        Optional<Credit> optionalUser = creditRepository.findById(id);
        if (optionalUser.isPresent()) {
            creditRepository.deleteById(id);
        } else {
            throw new CreditNotFoundException("id: " + id.toString());
        }
    }
}
