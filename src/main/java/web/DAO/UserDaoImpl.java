package web.DAO;

import org.springframework.stereotype.Repository;
import web.Model.User;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

   /* @Override
    public User findByLastName(String lastname) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.lastName = :username", User.class);
        return query.setParameter("username", lastname).getSingleResult();
    }*/

    @Override
    public User findByLastName(String lastname) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.lastName = :userName", User.class)
                .setParameter("userName", lastname)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> list;
        list = entityManager.createQuery("from User").getResultList();
        System.out.println(list);
        return list;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}