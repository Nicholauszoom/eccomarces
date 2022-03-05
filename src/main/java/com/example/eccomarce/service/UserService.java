package com.example.eccomarce.service;

import com.example.eccomarce.model.User;
import com.example.eccomarce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void removeUserById(int id){
        userRepository.deleteById(id);
    }
}
