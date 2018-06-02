package nl.timgoes.dbservice.dbservicemysql.service.interfaces;

import nl.timgoes.dbservice.dbservicemysql.model.User;

public interface UserService {
    User findByUsername(String username);
    User createUser(String username);
    User deleteUser(String username);
    User deleteUser(Long id);
}
