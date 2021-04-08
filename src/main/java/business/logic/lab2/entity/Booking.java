package business.logic.lab2.entity;

import business.logic.lab2.utils.BookingState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOKING")
@Getter
@Setter
public class Booking {
    @Column(name = "booking_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="user_id")
    private User user;

    @Column(name = "hotel_id")
    private Long hotel;

    @Column(name = "guests")
    private Integer guests;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "check_in_date")
    private Date checkIn;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "check_out_date")
    private Date checkOut;

    @Column(name = "book_state")
    @Enumerated(EnumType.STRING)
    private BookingState state;

    @Column(name = "book_state_comment")
    private String comment;

    @Column(name = "hotel_confirm")
    private Boolean hotelConfirmation;

}
