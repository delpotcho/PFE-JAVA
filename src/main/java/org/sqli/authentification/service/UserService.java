package org.sqli.authentification.service;

import org.sqli.authentification.dto.UserDtoRequest;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.entitie.User;

public interface UserService {
    public UserDtoResponse FindUserByLoginAndPassword (UserDtoRequest userDtoRequest);
}
