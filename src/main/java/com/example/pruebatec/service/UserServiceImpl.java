package com.example.pruebatec.service;

import com.example.pruebatec.entity.User;
import com.example.pruebatec.error.EmailAlreadyExistException;
import com.example.pruebatec.error.InvalidEmailException;
import com.example.pruebatec.error.InvalidPasswordException;
import com.example.pruebatec.error.UserNotFoundException;
import com.example.pruebatec.repository.UserRepository;
import com.example.pruebatec.utils.EmailValidator;
import com.example.pruebatec.utils.JwtUtils;
import com.example.pruebatec.utils.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;





@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    //Servicio que Lista todos los usuarios.
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //Servicio que registra un usuario.
    @Override
    public User saveUser(User user)  throws InvalidPasswordException, InvalidEmailException, EmailAlreadyExistException {
        PasswordValidator passwordValidator= new PasswordValidator();
        EmailValidator emailValidator = new EmailValidator();
        if( !passwordValidator.validatePasswordCustom(user.getPassword())){
            throw new InvalidPasswordException("El password no cumple con el formato. (Mínimo 8 caracteres. 1 dígito, 1 minúscula, 1 mayúscula, 1 caracter especial y sin espacios en blanco)");
        }
        if( !emailValidator.EmailValitadorCustom(user.getEmail())){
            throw new InvalidEmailException("Correo electrónico incorrecto. Ejemplo de formato: aaaaaa@dominio.cl");
        }

        Optional<User> userDb = userRepository.findUserByEmail(user.getEmail());

        if(!userDb.isEmpty()){
            throw new EmailAlreadyExistException("El correo electrónico ya existe");
        }

        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        user.setToken(jwtUtils.generateJwts(user));
        String currentDate = formatoFecha.format(calendario.getTime());
        user.setCreated(currentDate);
        user.setLast_login(currentDate);
        user.setModified("No modificado.");
        user.setIsactive(true);
        return userRepository.save(user);
    }

    //Servicio que actualiza un usuario.
    @Override
    public User updateUser(UUID id, User user) throws InvalidPasswordException, InvalidEmailException,UserNotFoundException{

        PasswordValidator passwordValidator= new PasswordValidator();
        EmailValidator emailValidator = new EmailValidator();
        if( !passwordValidator.validatePasswordCustom(user.getPassword())){
            throw new InvalidPasswordException("El password no cumple con el formato. (Mínimo 8 caracteres. 1 dígito, 1 minúscula, 1 mayúscula, 1 caracter especial y sin espacios en blanco)");
        }
        if( !emailValidator.EmailValitadorCustom(user.getEmail())){
            throw new InvalidEmailException("Correo electrónico incorrecto. Ejemplo de formato: aaaaaa@dominio.cl");
        }

        Optional<User> userExist = userRepository.findById(id);
        if(userExist.isEmpty()){
            throw new UserNotFoundException("Usuario no encontrado.");
        }


        User userDb = userRepository.findById(id).get();


        if(Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName()) ){
            userDb.setName(user.getName());
        }
        if(Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail()) ){
            userDb.setEmail(user.getEmail());
        }
        if(Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword()) ){
            userDb.setPassword(user.getPassword());
        }
        if(Objects.nonNull(user.getPhones()) && !user.getPhones().isEmpty()){
            userDb.setPhones(user.getPhones());
        }

        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        String modifiedDate = formatoFecha.format(calendario.getTime());
        userDb.setModified(modifiedDate);
        userDb.setLast_login(modifiedDate);
        return userRepository.save(userDb);
    }

    //Servicio que elimina un usuario.
    @Override
    public void deleteUser(UUID id) throws UserNotFoundException{

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("Usuario no encontrado.");
        }
    userRepository.deleteById(id);
    }

    //Servicio que busca un usuario por nombre.
    @Override
    public User findUserByName(String name) throws UserNotFoundException{
        Optional<User> user = userRepository.findUserByName(name);
        if(user.isEmpty()){
            throw new UserNotFoundException("Usuario no encontrado.");
        }
        return user.get();
    }



    //Servicio que busca un usuario por nombre sin importar mayúsculos o minúsculas.
    @Override
    public User findUserByNameIgnoreCase(String name) throws UserNotFoundException{
        Optional<User> user = userRepository.findUserByNameIgnoreCase(name);
        if(user.isEmpty()){
            throw new UserNotFoundException("Usuario no encontrado.");
        }
        return user.get();
    }


    //Servicio que busca un usuario por id.
    @Override
    public User findUserById(UUID id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("Usuario no encontrado.");
        }
        return user.get();
    }
}
