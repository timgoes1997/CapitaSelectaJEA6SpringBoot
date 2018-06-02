package nl.timgoes.dbservice.dbservicemysql.resource;

import nl.timgoes.dbservice.dbservicemysql.model.Credit;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/credit")
public class CreditResource {

    @Autowired
    private CreditService creditService;

    @GetMapping("/{name}")
    private Credit getCreditByName(@PathVariable("name") String name) {
        return creditService.findByName(name);
    }

    @PostMapping("create")
    private Credit createCredit(@RequestParam(value = "name") String name,
                                @RequestParam(value = "value") BigDecimal value) {
        return creditService.createCredit(name, value);
    }

    @DeleteMapping("delete/name/{name}")
    private void deleteCredit(@PathVariable(value = "name") String name) {
        creditService.deleteCredit(name);
    }

    @DeleteMapping("delete/{id}")
    private void deleteCreditById(@PathVariable(value = "id") Long id) {
        creditService.deleteCredit(id);
    }

}
