package com.travin.myshop.service;

import com.travin.myshop.domain.Role;
import com.travin.myshop.domain.User;
import com.travin.myshop.exception.InputDataException;
import com.travin.myshop.exception.UserAlreadyExistException;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public void updateData(
            User user,
            String username,
            String phone,
            String email,
            Map<String, String> form
    ) throws InputDataException, UserAlreadyExistException
    {
        if (username.isEmpty() || phone.isEmpty() || email.isEmpty() || form.isEmpty()) {
            throw new InputDataException("Incorrect data was entered. Try again");
        }
        if (userRepository.findByUsername(username)!=null) {
            throw new UserAlreadyExistException("This username is occupied");
        } else {
            user.setUsername(username);
            user.setPhone(phone);
            user.setEmail(email);
            user.getRoles().clear();
            Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

            for (String key : form.keySet()) {
                if (roles.contains(key)) {
                    user.getRoles().add(Role.valueOf(key));
                }
            }

            userRepository.save(user);
        }
    }
}
