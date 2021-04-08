package business.logic.lab2.repository;

import business.logic.lab2.entity.Booking;
import business.logic.lab2.utils.BookingState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> getAllByState(BookingState state);

    Optional<Booking> findById(Integer id);
}