package business.logic.lab2.repository;

import business.logic.lab2.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
