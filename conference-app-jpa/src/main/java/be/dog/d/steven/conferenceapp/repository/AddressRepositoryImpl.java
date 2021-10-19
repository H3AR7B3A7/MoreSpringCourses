package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }
}