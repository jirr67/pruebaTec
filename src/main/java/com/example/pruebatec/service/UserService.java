package com.example.pruebatec.service;

import com.example.pruebatec.entity.User;
import com.example.pruebatec.error.EmailAlreadyExistException;
import com.example.pruebatec.error.InvalidEmailException;
import com.example.pruebatec.error.InvalidPasswordException;
import com.example.pruebatec.error.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAllUsers();
    User saveUser(User user) throws InvalidPasswordException, InvalidEmailException, EmailAlreadyExistException;
    User updateUser(UUID id, User user) throws InvalidPasswordException, InvalidEmailException, UserNotFoundException;
    void deleteUser(UUID id) throws UserNotFoundException;
    User findUserByName(String name) throws UserNotFoundException;

    User findUserByNameIgnoreCase(String name)throws UserNotFoundException;
    User findUserById(UUID id) throws UserNotFoundException;
}
