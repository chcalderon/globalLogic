package com.example.demo.globallogic.userApi.controller;

import com.example.demo.globallogic.userApi.entity.UserEntity;
import com.example.demo.globallogic.userApi.model.Phone;
import com.example.demo.globallogic.userApi.model.ServiceException;
import com.example.demo.globallogic.userApi.model.User;
import com.example.demo.globallogic.userApi.model.UserResponse;
import com.example.demo.globallogic.userApi.repo.UserRepository;
import com.example.demo.globallogic.userApi.util.JavaUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api("API User GlobalLogic")
@RestController
public class ControllerApi {

    private static final Logger log = LoggerFactory.getLogger(ControllerApi.class);
    private final UserRepository userRepository;

    public ControllerApi(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity adminUser() throws Exception {
        User user = new User();
        user.setName("admin");
        user.setPassword("Admin123");
        user.setEmail("adasd@asd.cl");
        List<Phone> phones= new ArrayList<>();
        Phone phone = new Phone();
        phone.setNumber("3453453");
        phone.setCountrycode("56");
        phone.setCitycode("02");
        phones.add(phone);
        user.setPhones(phones);
        return userRepository.createUser(user);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User usuario)
            throws ServiceException {
        UserEntity userEntity;
        try {
            userEntity = userRepository.findByName(usuario.getName());
        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "Error at get token");
        }

        String token = "";
        try{
            if(userEntity.getPassword().contains(usuario.getPassword()))
                 token = JavaUtil.getJWTToken(usuario.getName());
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "Error at get token");
        }
        if (token.isEmpty())
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "Error at get token");
        return new ResponseEntity<String>(token,HttpStatus.OK);
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponse> addUser(@RequestBody User usuario) throws ServiceException {
        UserEntity entityUser;
        ServiceException serviceException;
       try{

           if (!JavaUtil.validatePassword(usuario.getPassword()))
               throw new ServiceException(HttpStatus.NOT_FOUND.value(), "La clave debe contener: Una Mayúscula, letras minúsculas, y dos números");
           if (!JavaUtil.validateEmail(usuario.getEmail()))
               throw new ServiceException(HttpStatus.NOT_FOUND.value(), "Debe ingresar un email válido");

           entityUser = userRepository.findByName(usuario.getName());
           if (entityUser!=null)
               throw new ServiceException(HttpStatus.ALREADY_REPORTED.value(), "El correo ya está registrado");

           entityUser = userRepository.createUser(usuario);


           }catch(Exception e){
           throw new ServiceException(HttpStatus.NOT_FOUND.value(), e.getMessage());

       }
        return new ResponseEntity<UserResponse>(parseToUserResponse(entityUser),HttpStatus.OK);
    }

    private UserResponse parseToUserResponse(UserEntity user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setCreated(user.getCreado().toString());
        userResponse.setId(user.getId());
        userResponse.setIsactive(user.isActive());
        userResponse.setLast_login(user.getLast_login().toString());
        userResponse.setToken(user.getToken());
        return userResponse;


    }


}
