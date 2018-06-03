package nl.timgoes.dbservice.modals.resource;

import nl.timgoes.dbservice.modals.model.Credit;
import nl.timgoes.dbservice.modals.service.interfaces.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditResource {

    @Autowired
    private CreditService creditService;

    @GetMapping("/{name}")
    private Credit getCreditByName(@PathVariable("name") String name) {
        return creditService.findByName(name);
    }

    @GetMapping("")
    private List<Credit> getCredits(){
        return creditService.getCredits();
    }

    @GetMapping("/names")
    private List<String> getNames(){
        return creditService.getCreditNames();
    }

    @PostMapping("/create")
    private Credit createCredit(@RequestParam(value = "name") String name,
                                @RequestParam(value = "value") BigDecimal value) {
        return creditService.createCredit(name, value);
    }

    @DeleteMapping("/delete/name/{name}")
    private void deleteCredit(@PathVariable(value = "name") String name) {
        creditService.deleteCredit(name);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteCreditById(@PathVariable(value = "id") Long id) {
        creditService.deleteCredit(id);
    }

}
