package nl.timgoes.dbservice.dbservicemysql.service;

import nl.timgoes.dbservice.dbservicemysql.exceptions.UserNotFoundException;
import nl.timgoes.dbservice.dbservicemysql.model.User;
import nl.timgoes.dbservice.dbservicemysql.repository.UserRepository;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.UserService;
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
        User user = new User(username, new Date());
        userRepository.save(user);
        return user;
    }

    @Override
    public User deleteUser(String username) {
        List<User> users = userRepository.findByName(username);
        if (users.size() < 1) throw new UserNotFoundException("name: " + username);

        User user = users.get(0);
        userRepository.delete(user);
        return user;
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.deleteById(id);
            return user;
        } else {
            throw new UserNotFoundException("id: " + id.toString());
        }
    }
}
