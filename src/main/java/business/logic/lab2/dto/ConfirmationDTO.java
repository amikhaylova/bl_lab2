package business.logic.lab2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ConfirmationDTO implements Serializable {
    @Min(value = 1, message = "Booking id must be grater than 1.")
    @NotNull
    private Integer bookingID;
}
