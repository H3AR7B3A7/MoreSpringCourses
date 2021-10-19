package be.dog.d.steven.conferenceapp.service;

import be.dog.d.steven.conferenceapp.model.Address;
import be.dog.d.steven.conferenceapp.model.User;
import be.dog.d.steven.conferenceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {

        Address address = new Address();
        address.setStreet("SesameStreet");
        address.setNumber(1969);
        address.setCity("Sesame Workshop");

        user.setAddress(address);

        return userRepository.save(user);
    }
}