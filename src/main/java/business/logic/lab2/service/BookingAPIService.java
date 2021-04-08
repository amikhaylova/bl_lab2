package business.logic.lab2.service;

import business.logic.lab2.configuration.BookingApiConfigurationProperties;
import business.logic.lab2.dto.BookingDTO;
import business.logic.lab2.entity.Booking;
import business.logic.lab2.exceptions.BookingFailException;
import business.logic.lab2.repository.BookingRepository;
import business.logic.lab2.utils.BookingEntityManager;
import business.logic.lab2.utils.BookingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class BookingAPIService {

    @Autowired
    private BookingApiConfigurationProperties bookingProperties;

    private final RestTemplate restTemplate;

    @Autowired
    private BookingRepository repository;
    @Autowired
    private BookingEntityManager bookingEntityManager;

    public BookingAPIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Transactional
    public void bookHotel(BookingDTO dto) {
        Booking booking = bookingEntityManager.transferFromDtoToBooking(dto);
        booking.setState(BookingState.WAITING);
        booking.setHotelConfirmation(false);
        repository.save(booking);
        HttpEntity<BookingDTO> request = new HttpEntity<>(dto);
        ResponseEntity<?> response = restTemplate
                .exchange(bookingProperties.getUrl(), HttpMethod.POST, request, BookingDTO.class);
       /* System.out.println(response.getStatusCode());
        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(response.getStatusCode());
            throw new BookingFailException("Booking failed.");
        }*/
        booking.setHotelConfirmation(true);
        repository.save(booking);
    }

    public boolean cancelBooking(BookingDTO dto){
        HttpEntity<BookingDTO> request = new HttpEntity<>(dto);
        ResponseEntity<?> response = restTemplate
                .exchange(bookingProperties.getCancel_url(), HttpMethod.POST, request, BookingDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            throw new BookingFailException("Booking cancel failed.");
        }

    }
}
