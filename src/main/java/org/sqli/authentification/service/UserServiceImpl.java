package org.sqli.authentification.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqli.authentification.dao.UserRepository;
import org.sqli.authentification.dto.UserDtoRequest;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.entitie.User;
import org.sqli.authentification.exception.ResponseMessage;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDtoResponse  FindUserByLoginAndPassword(UserDtoRequest userDtoRequest) {
        //1. Authentification : Best case
        User user = modelMapper.map(userDtoRequest, User.class);
        User getEntityUser = userRepository.findByLogin(user.getLogin());
        //2. Authentification : Mot de passe ou login erronés
        if (getEntityUser !=null){

            if (getEntityUser.getPassword().equals(userDtoRequest.getPassword())){

                //3. Authentification : Utilisateur désactivé
                if (!getEntityUser.isEnabled()) {

                    throw new ResponseMessage("User disabled");
                }
                modelMapper.addMappings(new PropertyMap<User, UserDtoResponse>() {
                    @Override
                    protected void configure() {
                        map().setGroup(source.getGroup().getName());
                    }
                });
                return modelMapper.map(userRepository.findByLoginAndPassword(user.getLogin(),user.getPassword()), UserDtoResponse.class);
            }

        }
        throw new ResponseMessage("Authentication error");

    }
}
