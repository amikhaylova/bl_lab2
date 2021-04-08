package business.logic.lab2.service;

import business.logic.lab2.configuration.BookingApiConfigurationProperties;
import business.logic.lab2.dto.CancelDTO;
import business.logic.lab2.entity.Booking;
import business.logic.lab2.entity.User;
import business.logic.lab2.repository.BookingRepository;
import business.logic.lab2.repository.UserRepository;
import business.logic.lab2.utils.BookingEntityManager;
import business.logic.lab2.utils.BookingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeratorService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingAPIService bookingAPIService;

    @Autowired
    BookingEntityManager bookingEntityManager;


    public List<Booking> getWaitingBookings() {
        return bookingRepository.getAllByState(BookingState.WAITING);
    }

    public boolean confirmBooking(Integer bookingID) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingID);
        Booking booking = optionalBooking.orElse(null);
        if (booking != null && booking.getState() == BookingState.WAITING) {
            booking.setState(BookingState.CONFIRMED);
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

    public boolean cancelBooking(CancelDTO cancelDTO) {
        Optional<Booking> optionalBooking = bookingRepository.findById(cancelDTO.getBookingID());
        Booking booking = optionalBooking.orElse(null);
        if (booking != null && booking.getState() == BookingState.WAITING) {
            bookingAPIService.cancelBooking(bookingEntityManager.transferFromBookingToDto(booking));
            booking.setState(BookingState.CANCELED);
            booking.setComment(cancelDTO.getComment());
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }
}
