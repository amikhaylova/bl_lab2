package business.logic.lab2.utils;

import business.logic.lab2.entity.User;
import business.logic.lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityManager {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        User user;
        if (!userRepository.existsByEmail(email)) {
            User newUser = new User();
            newUser.setEmail(email);
            userRepository.save(newUser);
        }
        user = userRepository.findByEmail(email);
        return user;
    }
}
