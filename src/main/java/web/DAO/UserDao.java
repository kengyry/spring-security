package web.DAO;

import web.Model.User;
import java.util.List;

public interface UserDao {

    User findByLastName(String lastname);

    User findById(Long id);

    List<User> findAll();

    void saveUser(User user);

    void deleteById(Long id);

    void updateUser(User user);
}

