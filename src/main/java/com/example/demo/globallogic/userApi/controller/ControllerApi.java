package com.example.demo.globallogic.userApi.controller;

import com.example.demo.globallogic.userApi.entity.UserEntity;
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

@Api("API User GlobalLogic")
@RestController
public class ControllerApi {

    private static final Logger log = LoggerFactory.getLogger(ControllerApi.class);
    private final UserRepository userRepository;

    public ControllerApi(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponse> addUser(@RequestBody User usuario) throws ServiceException {
        UserEntity entityUser;

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
        return new ResponseEntity<>(parseToUserResponse(entityUser),HttpStatus.OK);
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
