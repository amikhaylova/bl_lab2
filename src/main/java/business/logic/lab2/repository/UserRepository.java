package business.logic.lab2.repository;

import business.logic.lab2.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
