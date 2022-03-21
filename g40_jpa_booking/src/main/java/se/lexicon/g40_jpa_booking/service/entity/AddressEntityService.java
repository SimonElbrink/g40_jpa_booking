package se.lexicon.g40_jpa_booking.service.entity;


import se.lexicon.g40_jpa_booking.model.dto.form.AddressForm;
import se.lexicon.g40_jpa_booking.model.entity.Address;

import java.util.List;

public interface AddressEntityService{

    Address persistOrChange(AddressForm addressForm);

    Address findById(String id);

    List<Address> findAll();

    void delete(String id);

}
