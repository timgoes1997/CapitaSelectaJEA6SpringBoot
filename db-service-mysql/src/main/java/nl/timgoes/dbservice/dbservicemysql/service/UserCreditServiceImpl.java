package nl.timgoes.dbservice.dbservicemysql.service;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;
import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.model.UserCredit;
import nl.timgoes.dbservice.dbservicemysql.repository.UserCreditRepository;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.CreditService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserCreditService;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserService;
import nl.timgoes.exceptionhandling.exceptions.UserCreditAlreadyExistsException;
import nl.timgoes.exceptionhandling.exceptions.UserCreditNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserCreditServiceImpl implements UserCreditService {

    @Autowired
    private CreditService creditService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreditRepository userCreditRepository;

    @Override
    public List<UserCredit> findByCredit(Credit credit) {
        return userCreditRepository.findAllByCredit(credit);
    }

    @Override
    public List<UserCredit> findByCredit(String creditName) {
        return findByCredit(creditService.findByName(creditName));
    }

    @Override
    public List<UserCredit> findByUser(User user) {
        return userCreditRepository.findAllByUser(user);
    }

    @Override
    public List<UserCredit> findByUser(String userName) {
        return findByUser(userService.findByUsername(userName));
    }

    @Override
    public UserCredit findByCreditAndUser(Credit credit, User user) {
        return userCreditRepository.findByUserAndCredit(user, credit);
    }

    @Override
    public UserCredit findByCreditAndUser(String creditName, String userName) {
        return findByCreditAndUser(
                creditService.findByName(creditName),
                userService.findByUsername(userName));
    }

    @Override
    public UserCredit createUserCredit(User user, Credit credit, BigDecimal amount) {
        UserCredit exists = findByCreditAndUser(credit, user);
        if (exists != null) {
            throw new UserCreditAlreadyExistsException("User " + exists.getUser().getName() + " already has " + exists.getAmount().toString() + " of " + exists.getCredit().getName());
        }
        UserCredit userCredit = new UserCredit(user, credit, amount);
        return userCreditRepository.save(userCredit);
    }

    @Override
    public UserCredit createUserCredit(String creditName, String userName, BigDecimal amount) {
        return createUserCredit(
                userService.findByUsername(userName),
                creditService.findByName(creditName),
                amount);
    }

    @Override
    public UserCredit updateUserCredit(User user, Credit credit, BigDecimal amount) {
        UserCredit exists = findByCreditAndUser(credit, user);
        if (exists == null) {
            throw new UserCreditNotFoundException("For: User " + user.getName() + " and Credit " + credit.getName());
        }

        exists.setAmount(amount);
        return userCreditRepository.save(exists);
    }

    @Override
    public UserCredit updateUserCredit(String creditName, String userName, BigDecimal amount) {
        return updateUserCredit(
                userService.findByUsername(userName),
                creditService.findByName(creditName),
                amount);
    }
}