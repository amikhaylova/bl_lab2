package business.logic.lab2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CancelDTO {
    @NotNull
    @Min(value = 1, message = "Booking id must be grater than 1.")
    private Integer bookingID;

    @NotNull
    private String comment;
}
