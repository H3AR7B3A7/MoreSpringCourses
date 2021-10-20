package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.Registration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Registration> findAll() {
        String sql = "SELECT r FROM Registration r";
        return (List<Registration>) entityManager.createQuery(sql).getResultList();
    }

    public Registration save(Registration registration) {
        entityManager.persist(registration);
        return registration;
    }
}