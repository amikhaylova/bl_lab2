package business.logic.lab2.service;

import business.logic.lab2.entity.Role;
import business.logic.lab2.entity.ServiceStaff;
import business.logic.lab2.exceptions.InvalidLoginException;
import business.logic.lab2.exceptions.InvalidPasswordException;
import business.logic.lab2.exceptions.LoginExistsException;
import business.logic.lab2.repository.RoleRepository;
import business.logic.lab2.repository.ServiceStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceStaffService {
    @Autowired
    private ServiceStaffRepository serviceStaffRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServiceStaff saveServiceStaff(ServiceStaff serviceStaff) {
        //TODO not only moderator, but also admin saving
        if (!serviceStaffRepository.existsByLogin(serviceStaff.getLogin())){
            Role serviceStaffRole = roleRepository.findByName("ROLE_MODERATOR");
            serviceStaff.setRole(serviceStaffRole);
            serviceStaff.setPassword(passwordEncoder.encode(serviceStaff.getPassword()));
            return serviceStaffRepository.save(serviceStaff);
        }else throw new LoginExistsException("Login "+ serviceStaff.getLogin() + " already exists.");
    }

    public ServiceStaff findByLogin(String login) {
        return  serviceStaffRepository.findByLogin(login);
    }

    public ServiceStaff findByLoginAndPassword(String login, String password) {
        ServiceStaff serviceStaff = findByLogin(login);
        if (serviceStaff != null) {
            if (passwordEncoder.matches(password, serviceStaff.getPassword())) {
                return serviceStaff;
            }else throw new InvalidPasswordException("Invalid password");
        }else
            throw new InvalidLoginException("User with login " + login + " does not exist.");
    }
}
