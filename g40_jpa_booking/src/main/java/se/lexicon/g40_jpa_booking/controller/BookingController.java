package se.lexicon.g40_jpa_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g40_jpa_booking.model.dto.form.BookingForm;
import se.lexicon.g40_jpa_booking.model.entity.Booking;
import se.lexicon.g40_jpa_booking.service.entity.BookingEntityService;

import javax.validation.Valid;

@RestController
public class BookingController {

    private final BookingEntityService bookingEntityService;

    @Autowired
    public BookingController(BookingEntityService bookingEntityService) {
        this.bookingEntityService = bookingEntityService;
    }

    @PostMapping("/api/v1/booking")
    public ResponseEntity<Booking> create(@RequestBody BookingForm bookingForm){
        Booking createdBooking = bookingEntityService.create(bookingForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @GetMapping("/api/v1/booking/{id}")
    public ResponseEntity<Booking> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(bookingEntityService.findById(id));
    }

    @DeleteMapping("/api/v1/booking/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        bookingEntityService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
