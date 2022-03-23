package se.lexicon.g40_jpa_booking.service.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.model.dto.view.AddressDTO;
import se.lexicon.g40_jpa_booking.model.dto.view.BookingDTO;
import se.lexicon.g40_jpa_booking.model.dto.view.PremisesDTO;
import se.lexicon.g40_jpa_booking.model.entity.Address;
import se.lexicon.g40_jpa_booking.model.entity.Booking;
import se.lexicon.g40_jpa_booking.model.entity.Premises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class EntityToDTOConverter {

    public PremisesDTO toFullPremisesDTO(Premises premises){

        PremisesDTO premisesDTO = new PremisesDTO(
                premises.getId(),
                premises.getName(),
                toAddressDTO(premises.getAddress()),
                null
                );

        List<BookingDTO> bookingDTOS = new ArrayList<>();
        if (!premises.getBookings().isEmpty()){
            bookingDTOS = premises.getBookings().stream()
                    .map(this::toSmallBookingDTO)
                    .collect(Collectors.toList());
        }

        premisesDTO.setBookings(bookingDTOS);

        return premisesDTO;
    }

    public AddressDTO toAddressDTO(Address address){
        return new AddressDTO(
                address.getId(),
                address.getStreetAddress(),
                address.getZipCode(),
                address.getCity()
        );
    }

    public BookingDTO toSmallBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setDateTime(booking.getDateTime());
        bookingDTO.setPrice(booking.getPrice());
        bookingDTO.setAdministratorId(booking.getAdministratorId());
        bookingDTO.setVaccineType(booking.getVaccineType());
        bookingDTO.setVacant(booking.isVacant());
        //Not including Relationship to Premises

        return bookingDTO;
    }


}
