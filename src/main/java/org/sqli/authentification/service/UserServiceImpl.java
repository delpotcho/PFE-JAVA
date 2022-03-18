package org.sqli.authentification.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqli.authentification.dao.UserRepository;
import org.sqli.authentification.dto.UserDtoRequest;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.entitie.User;
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDtoResponse create(UserDtoRequest userDtoRequest) {
        User useEntity = modelMapper.map(userDtoRequest,User.class);
        User save = userRepository.save(useEntity);


        return  modelMapper.map(save ,UserDtoResponse.class);
    }

    @Override
    public void delete(UserDtoRequest userDtoRequest) {
        User useEntity = modelMapper.map(userDtoRequest,User.class);
        userRepository.delete(useEntity);


    }

    @Override
    public UserDtoResponse getUser(UserDtoRequest userDtoRequest) {
        User useEntity = modelMapper.map(userDtoRequest, User.class);
        User get = userRepository.findByLoginAndPassword(useEntity.getLogin(), useEntity.getPassword());

        return modelMapper.map(get, UserDtoResponse.class);
    }
}
