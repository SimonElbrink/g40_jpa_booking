package se.lexicon.g40_jpa_booking.service.entity;



import se.lexicon.g40_jpa_booking.model.dto.form.BookingForm;
import se.lexicon.g40_jpa_booking.model.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingEntityService extends GenericEntityService<Booking, String, BookingForm>{
    List<Booking> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Booking> findByDateTimeBefore(LocalDateTime end);
    List<Booking> findByDateTimeAfter(LocalDateTime start);
    List<Booking> findByAdministratorId(String administratorId);
    List<Booking> findByVaccineType(String vaccineType);
    List<Booking> findByVacantStatus(boolean vacantStatus);
    List<Booking> findAvailableTimesInCity(String city);
}
