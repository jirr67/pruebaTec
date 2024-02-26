package com.example.pruebatec.controller;

import com.example.pruebatec.entity.User;

import com.example.pruebatec.error.EmailAlreadyExistException;
import com.example.pruebatec.error.InvalidEmailException;
import com.example.pruebatec.error.InvalidPasswordException;
import com.example.pruebatec.error.UserNotFoundException;
import com.example.pruebatec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//Controlador para los endpoints
@RestController
public class UserController {

    @Autowired
    UserService userService;

    //Buscar todos los usuarios
    @GetMapping("/findAllUsers")
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    //Parte clave: Registro de usuarios
    @PostMapping("/saveUser")
    public User saveUser( @RequestBody User user) throws InvalidPasswordException, InvalidEmailException, EmailAlreadyExistException {
      return  userService.saveUser(user);
    }

    //Actualizar por ID
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) throws InvalidPasswordException, InvalidEmailException,UserNotFoundException{
        return userService.updateUser(id,user);
    }

    //Eliminar por ID
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable UUID id) throws UserNotFoundException{
         userService.deleteUser(id);
        return "Registro Eliminado.";
    }

    //Buscar por nombre
    @GetMapping("/findUserByName/{name}")
    public User findUserByName(@PathVariable String name)  throws UserNotFoundException{
        return  userService.findUserByName(name);
    }

    //Buscar por ID
    @GetMapping("/findUserById/{id}")
    public User findUserById(@PathVariable UUID id) throws UserNotFoundException{
        return  userService.findUserById(id);
    }

    //Buscar por nombre ignorando Mayúsculas y minísculas.
    @GetMapping("/findUserByNameIgnoreCase/{name}")
    public User findUserByNameIgnoreCase(@PathVariable String name)  throws UserNotFoundException{
        return  userService.findUserByNameIgnoreCase(name);
    }
}
