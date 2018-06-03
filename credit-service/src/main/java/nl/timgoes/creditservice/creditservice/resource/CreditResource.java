package nl.timgoes.creditservice.creditservice.resource;

import nl.timgoes.dbservice.modals.constant.Constant;
import nl.timgoes.dbservice.modals.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("")
public class CreditResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{name}")
    private Credit getCredit(@PathVariable("name") String name) {
        String url = Constant.DB_SERVICE_URL + "/credit/" + name;

        ResponseEntity<Credit> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Credit>() {
                });

        return userResponseEntity.getBody();
    }

    @PostMapping("/create")
    private Credit createCredit(@RequestParam(value = "name") String name,
                            @RequestParam(value = "value") BigDecimal value) {
        String url = Constant.DB_SERVICE_URL + "/credit/create";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", name);
        params.add("value", value.toString());

        return restTemplate.postForObject(url, params, Credit.class);
    }

    @DeleteMapping("/delete/name/{name}")
    private void deleteCredit(@PathVariable(value = "name") String name) {
        restTemplate.delete( Constant.DB_SERVICE_URL + "/credit/delete/name/" + name);
    }

    @DeleteMapping("/credit/delete/{id}")
    private void deleteUserById(@PathVariable(value = "id") Long id) {
        restTemplate.delete(Constant.DB_SERVICE_URL + "/credit/delete/" + id.toString());
    }

}
