package nl.timgoes.dbservice.dbservicemysql.service;

import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.repository.UserRepository;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserService;
import nl.timgoes.exceptionhandling.exceptions.UserNameAlreadyExistsException;
import nl.timgoes.exceptionhandling.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        List<User> users = userRepository.findByName(username);
        if (users.size() < 1) throw new UserNotFoundException("name: " + username);
        return users.get(0);
    }

    @Override
    public User createUser(String username) {
        if(userRepository.findByName(username).size() > 0){
            throw new UserNameAlreadyExistsException("name: " + username);
        }
        return userRepository.save(new User(username, new Date()));
    }

    @Override
    public void deleteUser(String username) {
        List<User> users = userRepository.findByName(username);
        if (users.size() < 1) throw new UserNotFoundException("name: " + username);
        User user = users.get(0);
        userRepository.delete(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("id: " + id.toString());
        }
    }
}
