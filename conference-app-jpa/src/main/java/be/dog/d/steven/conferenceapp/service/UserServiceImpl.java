package be.dog.d.steven.conferenceapp.service;

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
        return userRepository.save(user);
    }
}