package org.sqli.authentification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.sqli.authentification.dto.UserDtoRequest;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.entitie.User;
import org.sqli.authentification.service.UserService;

public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public  ResponseEntity<UserDtoResponse> createCompte (@RequestBody UserDtoRequest userDtoRequest){


        UserDtoResponse userDtoResponse = userService.create(userDtoRequest);

        return new ResponseEntity<>(userDtoResponse , HttpStatus.CREATED) ;
    }
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteCompte (@RequestBody UserDtoRequest userDtoRequest){

        userService.delete(userDtoRequest);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }
}
