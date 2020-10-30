package web.service;

import web.Model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<Role> getRoles(Set<String> role);

    Role getRole(String name);

    Set<Role> findAllRoles();
}
