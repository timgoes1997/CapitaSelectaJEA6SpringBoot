package nl.timgoes.dbservice.dbservicemysql.resource;

import nl.timgoes.dbservice.dbservicemysql.exceptions.UserNotFoundException;
import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.repository.UserRepository;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    private User getUserByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }

    @PostMapping("create")
    private User createUser(@RequestParam(value="name") String username){
        return userService.createUser(username);
    }

    @DeleteMapping("delete")
    private User deleteUser(@RequestParam(value="name") String username){
        return userService.deleteUser(username);
    }

    @DeleteMapping("delete/{id}")
    private User deleteUserById(@PathVariable(value="id") Long id){
        return userService.deleteUser(id);
    }

}
