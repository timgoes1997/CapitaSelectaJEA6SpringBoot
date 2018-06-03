package nl.timgoes.dbservice.modals.resource;

import nl.timgoes.dbservice.modals.model.User;
import nl.timgoes.dbservice.modals.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("delete/name/{name}")
    private void deleteUser(@PathVariable(value="name") String username){
        userService.deleteUser(username);
    }

    @DeleteMapping("delete/{id}")
    private void deleteUserById(@PathVariable(value="id") Long id){
        userService.deleteUser(id);
    }

}
