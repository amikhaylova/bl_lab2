package business.logic.lab2.controller;

import business.logic.lab2.dto.BookingDTO;
import business.logic.lab2.dto.CancelDTO;
import business.logic.lab2.dto.ConfirmationDTO;
import business.logic.lab2.entity.Booking;
import business.logic.lab2.exceptions.BookingFailException;
import business.logic.lab2.exceptions.ModerationFailedException;
import business.logic.lab2.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
public class ModeratorController {

    @Autowired
    ModeratorService moderatorService;

    @GetMapping(path="/waiting_list")
    public List<Booking> getWaitingList(){
        return moderatorService.getWaitingBookings();
    }

    @PostMapping(path="/confirm")
    public ResponseEntity<?> confirmBooking (@RequestBody ConfirmationDTO confirmationDTO) {
        if (!moderatorService.confirmBooking(confirmationDTO.getBookingID()))
            throw new ModerationFailedException("Confirmation failed.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path="/cancel")
    public ResponseEntity<?> cancelBooking (@RequestBody CancelDTO cancelDTO) {
        if (!moderatorService.cancelBooking(cancelDTO))
            throw new ModerationFailedException("Cancel process failed.");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
