package com.travin.myshop.service;

import com.travin.myshop.domain.Product;
import com.travin.myshop.domain.User;
import com.travin.myshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ProductService {
    @Autowired
    UserRepository userRepository;

    public void addToCart(Product product, Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        user.getCart().getProducts().add(product);
        userRepository.save(user);
    }
}
