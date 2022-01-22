package com.example.ShopUp.Repository;


import com.example.ShopUp.model.Role;
import com.example.ShopUp.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findUserByRoles(Role role);
    User findUserById(Long id);
}
