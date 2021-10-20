package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.Registration;
import be.dog.d.steven.conferenceapp.model.RegistrationReport;
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
        String jpql = "SELECT r FROM Registration r";
        return (List<Registration>) entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<RegistrationReport> findAllReports() {
//        String jpql = "SELECT new be.dog.d.steven.conferenceapp.model.RegistrationReport(r.name, c.name, c.description) " +
//                "FROM Registration r, Course c WHERE r.id = c.registration.id";
//        return (List<RegistrationReport>) entityManager.createQuery(jpql).getResultList();
        return (List<RegistrationReport>) entityManager.createNamedQuery(Registration.REGISTRATION_REPORT).getResultList();
    }

    public Registration save(Registration registration) {
        entityManager.persist(registration);
        return registration;
    }
}