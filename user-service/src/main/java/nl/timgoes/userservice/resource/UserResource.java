package nl.timgoes.userservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import nl.timgoes.dbservice.dbservicemysql.constant.Constant;
import nl.timgoes.dbservice.dbservicemysql.model.*;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("")
public class UserResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String userName) {
        String url = Constant.DB_SERVICE_URL + "/user/" + userName;

        ResponseEntity<User> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {
                });

        return userResponseEntity.getBody();
    }

    public String fallback(){
        return "This is a fallbackMethod";
    }

    @PostMapping("/create")
    private User createUser(@RequestParam(value="name") String username){
        String url = Constant.DB_SERVICE_URL + "/user/create";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", username);

        return restTemplate.postForObject(url, params, User.class);
    }

    @DeleteMapping("/delete")
    private void deleteUser(@RequestParam(value="name") String username){
        String url = Constant.DB_SERVICE_URL + "/user/delete/name/" + username;
        restTemplate.delete(url);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteUserById(@PathVariable(value="id") Long id){
        String url = Constant.DB_SERVICE_URL + "/user/delete/" + id.toString();
        restTemplate.delete(url);
    }

}
