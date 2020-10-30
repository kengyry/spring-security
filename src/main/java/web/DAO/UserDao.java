package web.DAO;

import web.Model.Role;
import web.Model.User;
import java.util.List;
import java.util.Set;

public interface UserDao {

    User findByLastName(String lastname);

    User findById(Long id);

    List<User> findAll();

    void saveUser(User user);

    void deleteById(Long id);

    void updateUser(User user);


}

