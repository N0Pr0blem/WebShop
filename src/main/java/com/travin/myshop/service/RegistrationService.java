package com.travin.myshop.service;

import com.travin.myshop.domain.Role;
import com.travin.myshop.domain.User;
import com.travin.myshop.exception.InputDataException;
import com.travin.myshop.exception.UserAlreadyExistException;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service

public class RegistrationService {
    @Autowired
    UserRepository userRepository;

    public void addUser(User user) throws UserAlreadyExistException, InputDataException {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            throw new UserAlreadyExistException("User is already exist");
        }
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            throw new InputDataException("Wrong username or password");
        }
        if (!user.getUsername().isEmpty() && !user.getPassword().isEmpty()) {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
        }
    }
}
