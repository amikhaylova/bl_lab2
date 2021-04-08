package business.logic.lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Lab2Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "admin";
        System.out.println(passwordEncoder.encode(password));*/
    }

}
