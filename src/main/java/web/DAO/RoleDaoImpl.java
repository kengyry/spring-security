package web.DAO;

import org.springframework.stereotype.Repository;
import web.Model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public Set<Role> getRoles(Set<String> role) {
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r WHERE r.name in (:role)")
                .setParameter("role", role)
                .getResultList());
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r from Role r", Role.class)
                .getResultList()); //.stream().filter(distinctByKey(Role::getRole)).collect(Collectors.toSet());
    }

    @Override
    public Role getRole(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :roleName", Role.class)
                .setParameter("roleName", name)
                .setMaxResults(1)
                .getSingleResult();
    }
}
