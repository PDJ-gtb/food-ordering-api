package com.restaurant.order.api.repository;

import com.restaurant.order.api.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    Optional<AppUser>findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
    @Query("select u from AppUser u left join fetch u.roles where u.username = :username")
    Optional<AppUser> findByUsernameWithRoles(@Param("username") String username);

}
