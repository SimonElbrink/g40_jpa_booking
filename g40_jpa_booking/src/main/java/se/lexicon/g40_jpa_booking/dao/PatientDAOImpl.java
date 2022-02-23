package se.lexicon.g40_jpa_booking.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.dao.interfaces.PatientDAO;
import se.lexicon.g40_jpa_booking.model.entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
@Repository
public class PatientDAOImpl implements PatientDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Patient save(Patient patient) {
        if (patient == null) throw new IllegalArgumentException("Patient is Null!");

        if (patient.getId() == null){
            entityManager.persist(patient);
        }else {
            return entityManager.merge(patient);
        }
        return patient;
    }

    @Override
    @Transactional
    public Optional<Patient> findById(String id) {
        Patient patient = entityManager.find(Patient.class, id);
        return Optional.ofNullable(patient);
    }

    @Override
    @Transactional
    public List<Patient> findAll() {
        return entityManager.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Patient> findByPnr(String pnr) {
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.pnr = :pnr", Patient.class)
                .setParameter("pnr", pnr.replaceAll(" ", "").replaceAll("-", "").trim())
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public List<Patient> findByName(String name) {
        return entityManager.createQuery("SELECT p FROM Patient p WHERE LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT('%', :name, '%')) ", Patient.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<Patient> found = findById(id);
        if (found.isPresent()){
            entityManager.remove(found.get());
        }

    }
}
