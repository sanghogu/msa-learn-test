package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.pojo.RequestUser;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(RequestUser requestUser){

        User user = new User(requestUser.getEmail(), requestUser.getName(), requestUser.getPassword());

        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
