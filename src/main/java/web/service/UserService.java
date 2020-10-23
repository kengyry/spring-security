package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.Model.Role;
import web.Model.User;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    User findByLastName(String lastname);

    User findById(Long id);

    List<User> findAll();

    void saveUser(User user);

    void deleteById(Long id);

    void updateUser(User user);

    Set<Role> getRoles(Set<String> role);

    Role getRole(String name);


}
