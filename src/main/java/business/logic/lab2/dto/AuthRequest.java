package business.logic.lab2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class AuthRequest implements Serializable {
    @NotNull
    private String password;
    @NotNull
    private String login;
}
