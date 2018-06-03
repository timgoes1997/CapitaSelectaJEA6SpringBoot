package nl.timgoes.dbservice.resource;

import nl.timgoes.core.model.UserCredit;
import nl.timgoes.dbservice.service.interfaces.UserCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/credit")
public class UserCreditResource {

    @Autowired
    private UserCreditService userCreditService;

    @GetMapping("/{userName}")
    private List<UserCredit> getUserCredits(@PathVariable(value = "userName") String userName) {
        return userCreditService.findByUserOrdered(userName);
    }

    @GetMapping("/{userName}/ordered/default")
    private List<UserCredit> getUserCreditsNonOrdered(@PathVariable(value = "userName") String userName) {
        return userCreditService.findByUser(userName);
    }

    @GetMapping("/{userName}/{creditName}")
    private UserCredit getUserCredit(@PathVariable(value = "userName") String userName,
                                     @PathVariable(value = "creditName") String creditName) {
        return userCreditService.findByCreditAndUser(creditName, userName);
    }

    /*
    @PostMapping("/create")
    private UserCredit createUserCredit(@RequestParam(value = "userName") String userName,
                                        @RequestParam(value = "creditName") String creditName,
                                        @RequestParam(value = "amount") BigDecimal amount) {
        return userCreditService.createUserCredit(creditName, userName, amount);
    }

    @PostMapping("/update")
    private UserCredit updateUserCredit(@RequestParam(value = "userName") String userName,
                                        @RequestParam(value = "creditName") String creditName,
                                        @RequestParam(value = "amount") BigDecimal amount) {
        return userCreditService.updateUserCredit(creditName, userName, amount);
    }*/

}
