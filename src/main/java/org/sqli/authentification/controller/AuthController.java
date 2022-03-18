package org.sqli.authentification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.sqli.authentification.dto.UserDtoRequest;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.service.UserService;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/auth")
    public ResponseEntity<UserDtoResponse> authantification (@RequestBody UserDtoRequest userDtoRequest){

        UserDtoResponse userDtoResponse = userService.getUser(userDtoRequest);



return new ResponseEntity<>(userDtoResponse ,HttpStatus.OK);
    }


}
