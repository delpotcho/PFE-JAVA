package org.sqli.authentification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.dto.UserFormDtoRequest;
import org.sqli.authentification.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDtoResponse> createCompte(@RequestBody UserFormDtoRequest userFormDtoRequest) {
        UserDtoResponse userDtoResponse = userService.create(userFormDtoRequest);

        return new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);
    }
}
