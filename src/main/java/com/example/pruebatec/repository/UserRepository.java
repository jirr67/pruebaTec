package com.example.pruebatec.repository;

import com.example.pruebatec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByNameIgnoreCase(String name);
}
