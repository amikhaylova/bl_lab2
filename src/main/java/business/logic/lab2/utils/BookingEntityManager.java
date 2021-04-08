package business.logic.lab2.utils;

import business.logic.lab2.dto.BookingDTO;
import business.logic.lab2.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingEntityManager {

    @Autowired
    private UserEntityManager userEntityManager;

    public Booking transferFromDtoToBooking(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setUser(userEntityManager.getUserByEmail(dto.getEmail()));
        booking.setCheckIn(dto.getCheckIn());
        booking.setCheckOut(dto.getCheckOut());
        booking.setGuests(dto.getGuests());
        booking.setHotel(dto.getHotelID());
        return booking;
    }

    public BookingDTO transferFromBookingToDto (Booking booking){
        BookingDTO dto = new BookingDTO();
        dto.setCheckIn(booking.getCheckIn());
        dto.setCheckOut(booking.getCheckOut());
        dto.setEmail(booking.getUser().getEmail());
        dto.setHotelID(booking.getHotel());
        dto.setGuests(booking.getGuests());
        return dto;
    }

}
