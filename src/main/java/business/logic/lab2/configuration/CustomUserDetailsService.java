package business.logic.lab2.configuration;

import business.logic.lab2.entity.ServiceStaff;
import business.logic.lab2.service.ServiceStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ServiceStaffService serviceStaffService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServiceStaff serviceStaff = serviceStaffService.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(serviceStaff);
    }
}