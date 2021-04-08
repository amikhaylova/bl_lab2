package business.logic.lab2.controller;

import business.logic.lab2.configuration.jwt.JwtProvider;
import business.logic.lab2.dto.AuthRequest;
import business.logic.lab2.dto.AuthResponse;
import business.logic.lab2.entity.ServiceStaff;
import business.logic.lab2.service.ServiceStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
public class AuthenticationController {
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    ServiceStaffService service;

    @PostMapping(path="/auth")
    public AuthResponse authentication(@RequestBody AuthRequest authRequest) {
        ServiceStaff staff = service.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        String token = jwtProvider.generateToken(staff.getLogin());
        return new AuthResponse(token);
    }

    @PostMapping(path="/register_moderator")
    public ResponseEntity<?>  registration(@RequestHeader("Authorization") @RequestBody AuthRequest regRequest) {
        ServiceStaff staff = new ServiceStaff();
        staff.setLogin(regRequest.getLogin());
        staff.setPassword(regRequest.getPassword());
        if (service.saveServiceStaff(staff) != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
