package web.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.Model.Role;
import web.Model.User;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final RoleDao roleDao;

    @Autowired
    public UserDaoImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByLastName(String lastname) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.lastName = :username", User.class);
        return query.setParameter("username", lastname).getSingleResult();
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
        return list;
    }

    @Override
    public void saveUser(User user) {
        for (Role userRole : user.getRoles()) {
            for (Role dbRole : roleDao.findAllRoles()) {
                if (dbRole.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(dbRole.getId());
                }
            }
        }
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
        for (Role userRole : user.getRoles()) {
            for (Role dbRole : roleDao.findAllRoles()) {
                if (dbRole.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(dbRole.getId());
                }
            }
        }
        entityManager.merge(user);
    }

}




