package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.Model.User;
import java.util.List;

public interface UserService extends UserDetailsService {

    User findByLastName(String lastname);

    User findById(Long id);

    List<User> findAll();

    void saveUser(User user);

    void deleteById(Long id);

    void updateUser(User user);




}
