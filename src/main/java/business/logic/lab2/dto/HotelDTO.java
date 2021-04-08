package business.logic.lab2.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HotelDTO implements Serializable {
    private long id;
    private String name;
    private String price;

}
