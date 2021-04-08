package business.logic.lab2.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BookingDTO implements Serializable {

    @NotNull
    private Long hotelID;

    @NotNull
    @Min(value = 1, message = "Number of guests must be greater than 0")
    private Integer guests;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkIn;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOut;

    @NotNull
    @Email()
    private String email;

}
