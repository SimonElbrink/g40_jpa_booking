package se.lexicon.g40_jpa_booking.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.dao.interfaces.AddressDAO;
import se.lexicon.g40_jpa_booking.exception.AppResourceNotFoundException;
import se.lexicon.g40_jpa_booking.model.dto.form.AddressForm;
import se.lexicon.g40_jpa_booking.model.entity.Address;

import java.util.List;

@Service
@Transactional
public class AddressEntityServiceImpl implements AddressEntityService{

    private final AddressDAO addressDAO;

    @Autowired
    public AddressEntityServiceImpl(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    public Address persistOrChange(AddressForm addressForm) {
        if(addressForm == null) throw new IllegalArgumentException("AddressForm was null");

        return addressDAO.findByStreetZipCodeAndCity(
                addressForm.getStreetAddress().trim(),
                addressForm.getZipCode().replace(" ", ""),
                addressForm.getCity().trim()
        ).orElse(
                addressDAO.save(new Address(
                    null, addressForm.getStreetAddress().trim(), addressForm.getZipCode().replace(" ", ""), addressForm.getCity().trim()
                ))
        );
    }

    @Override
    public Address findById(String id) {
        return addressDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find address with id " + id));
    }

    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public void delete(String id) {
        addressDAO.deleteById(id);
    }
}
