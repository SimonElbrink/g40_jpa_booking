package se.lexicon.g40_jpa_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.g40_jpa_booking.dao.interfaces.AddressDAO;
import se.lexicon.g40_jpa_booking.dao.interfaces.BookingDAO;
import se.lexicon.g40_jpa_booking.dao.interfaces.PremisesDAO;
import se.lexicon.g40_jpa_booking.model.entity.Address;

@SpringBootApplication
public class G40JpaBookingApplication implements CommandLineRunner {
	@Autowired
	AddressDAO addressDAO;

	@Autowired
	BookingDAO bookingDAO;


	public static void main(String[] args) {
		SpringApplication.run(G40JpaBookingApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
//    	addressDAO.save(new Address(null, "somestreet 5", "123 45", "WayTown"));
//
//		System.out.println(addressDAO.findByStreetZipCodeAndCity("somestreet 5", "123 45", "WayTown"));

		System.out.println(bookingDAO.findAvailableBookingsInCity("Växjö"));
	}
}
