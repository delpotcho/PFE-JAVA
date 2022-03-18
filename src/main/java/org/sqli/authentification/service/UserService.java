package org.sqli.authentification.service;

import org.sqli.authentification.dto.UserDtoRequest;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.entitie.User;

public interface UserService {
    public UserDtoResponse create (UserDtoRequest userDtoRequest);
    public  void delete (UserDtoRequest userDtoRequest);
    public UserDtoResponse getUser (UserDtoRequest userDtoRequest);
}
