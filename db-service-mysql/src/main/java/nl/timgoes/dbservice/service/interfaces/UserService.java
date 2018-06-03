package nl.timgoes.dbservice.service.interfaces;

import nl.timgoes.core.model.*;

public interface UserService {
    User findByUsername(String username);
    User createUser(String username);
    void deleteUser(String username);
    void deleteUser(Long id);
}
