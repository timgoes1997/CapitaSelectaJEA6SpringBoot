package nl.timgoes.userservice.resource;

import nl.timgoes.dbservice.modals.constant.Constant;
import nl.timgoes.dbservice.modals.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("")
public class UserResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{username}")
    private User getUser(@PathVariable("username") String userName) {
        String url = Constant.DB_SERVICE_URL + "/user/" + userName;

        ResponseEntity<User> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/credits")
    private List<UserCredit> getUserCredits(@PathVariable(value = "username") String userName) {
        String url = Constant.DB_SERVICE_URL + "/user/credit/" + userName;

        ResponseEntity<List<UserCredit>> userCreditResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserCredit>>() {
                });

        return userCreditResponseEntity.getBody();
    }

    @GetMapping("/{username}/credits/default")
    private List<UserCredit> getUserCreditsNonOrdered(@PathVariable(value = "username") String userName) {
        String url = Constant.DB_SERVICE_URL + "/user/credit/" + userName + "/ordered/default";

        ResponseEntity<List<UserCredit>> userCreditResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserCredit>>() {
                });

        return userCreditResponseEntity.getBody();
    }

    @GetMapping("/{username}/credit/{creditname}")
    private UserCredit getUserCredit(@PathVariable(value = "username") String userName,
                                     @PathVariable(value = "creditname") String creditName) {
        String url = Constant.DB_SERVICE_URL + "/user/credit/" + userName + "/" + creditName;

        ResponseEntity<UserCredit> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserCredit>() {
                });

        return userResponseEntity.getBody();
    }

    @PostMapping("/create")
    private User createUser(@RequestParam(value = "name") String username) {
        String url = Constant.DB_SERVICE_URL + "/user/create";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", username);

        return restTemplate.postForObject(url, params, User.class);
    }

    @DeleteMapping("/delete")
    private void deleteUser(@RequestParam(value = "name") String username) {
        String url = Constant.DB_SERVICE_URL + "/user/delete/name/" + username;
        restTemplate.delete(url);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteUserById(@PathVariable(value = "id") Long id) {
        String url = Constant.DB_SERVICE_URL + "/user/delete/" + id.toString();
        restTemplate.delete(url);
    }

}
