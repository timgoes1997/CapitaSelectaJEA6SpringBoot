package nl.timgoes.dbservice.dbservicemysql.resource;

import com.netflix.discovery.converters.Auto;
import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.model.UserCredit;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user/credit")
public class UserCreditResource {

    @Autowired
    private UserCreditService userCreditService;

    @GetMapping("/{userName}/{creditName}")
    private UserCredit getUserCredit(@PathVariable(value="userName") String userName,
                                     @PathVariable(value="creditName") String creditName){
        return userCreditService.findByCreditAndUser(creditName,userName );
    }

    @PostMapping("/create")
    private UserCredit createUserCredit(@RequestParam(value="userName") String userName,
                                  @RequestParam(value="creditName") String creditName,
                                  @RequestParam(value="amount") BigDecimal amount){
        return userCreditService.createUserCredit(creditName, userName, amount);
    }

    @PostMapping("/update")
    private UserCredit updateUserCredit(@RequestParam(value="userName") String userName,
                                        @RequestParam(value="creditName") String creditName,
                                        @RequestParam(value="amount") BigDecimal amount){
        return userCreditService.updateUserCredit(creditName, userName, amount);
    }

}
