package se.lexicon.g40_jpa_booking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.dao.interfaces.AppRoleDAO;
import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.entity.AppRole;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AppRoleDAOImpl implements AppRoleDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppRoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<AppRole> findByUserRole(UserRole userRole) {
        return entityManager.createQuery("SELECT r FROM AppRole r WHERE r.Role = :role", AppRole.class)
                .setParameter("role", userRole.toString())
                .getResultStream()
                .findFirst();
    }

    @Override
    public AppRole save(AppRole entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        if(entity.getId() == null){
            entityManager.persist(entity);
        }else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<AppRole> findById(String id) {
        return Optional.ofNullable(
                entityManager.find(AppRole.class, id)
        );
    }

    @Override
    public List<AppRole> findAll() {
        return entityManager.createQuery("SELECT r FROM AppRole r", AppRole.class)
                .getResultList();
    }

    @Override
    public void delete(String id) {
        findById(id).ifPresent(entityManager::remove);
    }
}