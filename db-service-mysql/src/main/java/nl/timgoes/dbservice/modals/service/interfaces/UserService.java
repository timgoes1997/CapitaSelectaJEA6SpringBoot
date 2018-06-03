package nl.timgoes.dbservice.modals.service.interfaces;

import nl.timgoes.dbservice.modals.model.User;

public interface UserService {
    User findByUsername(String username);
    User createUser(String username);
    void deleteUser(String username);
    void deleteUser(Long id);
}
