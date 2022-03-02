package se.lexicon.g40_jpa_booking.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g40_jpa_booking.dao.interfaces.ContactInfoDAO;
import se.lexicon.g40_jpa_booking.exception.AppResourceNotFoundException;
import se.lexicon.g40_jpa_booking.model.dto.form.ContactInfoForm;
import se.lexicon.g40_jpa_booking.model.entity.ContactInfo;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoEntityServiceImpl implements ContactInfoEntityService{

    private final ContactInfoDAO contactInfoDAO;

    @Autowired
    public ContactInfoEntityServiceImpl(ContactInfoDAO contactInfoDAO) {
        this.contactInfoDAO = contactInfoDAO;
    }

    @Override
    public ContactInfo create(ContactInfoForm contactInfoForm) {

        if (contactInfoForm == null) throw new IllegalArgumentException("ContactInfoForm was null");

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(contactInfoForm.getEmail().trim());

       if (contactInfoForm.getPhone() != null){
           contactInfo.setPhone(contactInfoForm.getPhone());
       }
       contactInfo = contactInfoDAO.save(contactInfo);
       return contactInfo;

    }

    @Override
    public ContactInfo findById(String id) {
        return contactInfoDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find ContactInfo with provided ID"));
    }

    @Override
    public List<ContactInfo> findAll() {
        return contactInfoDAO.findAll();
    }

    @Override
    public ContactInfo update(String id, ContactInfoForm contactInfoForm) {
        ContactInfo contactInfo = findById(id);

        Optional<ContactInfo> optional = contactInfoDAO.findByEmail(contactInfoForm.getEmail());

        if (optional.isPresent() && !optional.get().getId().equals(id)){
            throw new IllegalArgumentException("Provided Email is already in use");
        }

        contactInfo.setEmail(contactInfoForm.getEmail().trim());
        contactInfo.setPhone(contactInfoForm.getPhone());

        return contactInfoDAO.save(contactInfo);
    }

    @Override
    public void delete(String id) {
        contactInfoDAO.delete(id);
    }
}
