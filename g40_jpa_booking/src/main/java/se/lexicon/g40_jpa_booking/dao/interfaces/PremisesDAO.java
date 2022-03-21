package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g40_jpa_booking.model.entity.Premises;

import java.util.List;

public interface PremisesDAO extends JpaRepository<Premises, String> {

    List<Premises> findAllByName(String name);
    long countUsagesByAddressId(String addressId);


}
