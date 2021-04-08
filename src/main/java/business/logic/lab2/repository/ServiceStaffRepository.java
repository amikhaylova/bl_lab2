package business.logic.lab2.repository;

import business.logic.lab2.entity.ServiceStaff;
import org.springframework.data.repository.CrudRepository;

public interface ServiceStaffRepository extends CrudRepository<ServiceStaff, Long> {
    ServiceStaff findByLogin(String login);
    Boolean existsByLogin(String login);
}
