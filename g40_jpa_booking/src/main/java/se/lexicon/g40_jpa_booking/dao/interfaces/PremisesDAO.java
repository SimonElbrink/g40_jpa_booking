package se.lexicon.g40_jpa_booking.dao.interfaces;

import se.lexicon.g40_jpa_booking.model.entity.Premises;

public interface PremisesDAO extends DAOGenericCRUD<Premises, String>{
    long countUsagesByAddressId(String addressId);
}
