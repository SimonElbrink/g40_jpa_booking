package se.lexicon.g40_jpa_booking.service.entity;

import java.util.List;

public interface GenericEntityService<R, ID, FORM> {

    R create(FORM form);
    R findById(ID id);
    List<R> findAll();
    R update(ID id, FORM form);
    void delete(ID id);

}
