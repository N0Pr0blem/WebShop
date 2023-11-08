package com.travin.myshop.repos;

import com.travin.myshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
