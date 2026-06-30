package com.restaurant.order.api.repository;

import com.restaurant.order.api.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    Optional<AppUser>findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
}
