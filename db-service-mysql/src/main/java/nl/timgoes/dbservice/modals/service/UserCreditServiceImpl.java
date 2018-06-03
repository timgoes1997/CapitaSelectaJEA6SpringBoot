package nl.timgoes.dbservice.modals.service;

import nl.timgoes.dbservice.modals.model.Credit;
import nl.timgoes.dbservice.modals.model.User;
import nl.timgoes.dbservice.modals.model.UserCredit;
import nl.timgoes.dbservice.modals.repository.UserCreditRepository;
import nl.timgoes.dbservice.modals.service.interfaces.CreditService;
import nl.timgoes.dbservice.modals.service.interfaces.UserCreditService;
import nl.timgoes.dbservice.modals.service.interfaces.UserService;
import nl.timgoes.exceptionhandling.exceptions.NotEnoughUserCreditException;
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
    public List<UserCredit> findByUserOrdered(User user) {
        return findByUserOrdered(user);
    }

    @Override
    public List<UserCredit> findByUserOrdered(String userName) {
        return userCreditRepository.findAllByUserOrderByLastUpdateDesc(userService.findByUsername(userName));
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

    @Override
    public UserCredit addToUserCredit(User user, Credit credit, BigDecimal amount) {
        UserCredit creditToAdd = findByCreditAndUser(credit, user);
        if(creditToAdd != null){
            return updateUserCredit(
                    creditToAdd.getUser(),
                    creditToAdd.getCredit(),
                    creditToAdd.getAmount().add(amount));
        }else{
            return createUserCredit(user, credit, amount);
        }
    }

    @Override
    public UserCredit transfer(UserCredit from, User to, BigDecimal amount) {
        updateUserCredit(
                from.getUser(),
                from.getCredit(),
                from.getAmount().subtract(amount));

        return addToUserCredit(to, from.getCredit(), amount);
    }

    @Override
    public UserCredit hasEnoughCreditForTransaction(User user, Credit credit, BigDecimal amount) {
        UserCredit userCreditToCheck = findByCreditAndUser(credit, user);
        if(userCreditToCheck ==  null){
            throw new UserCreditNotFoundException("User has no credits of given type");
        }
        if (userCreditToCheck.getAmount().compareTo(amount) < 0) {
            throw new NotEnoughUserCreditException("Your credit is to low by " + userCreditToCheck.getAmount().subtract(amount).toString());
        }
        return userCreditToCheck;
    }
}
