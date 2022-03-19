package org.sqli.authentification.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqli.authentification.dao.GroupRepository;
import org.sqli.authentification.dao.UserRepository;
import org.sqli.authentification.dto.UserDtoRequest;
import org.sqli.authentification.dto.UserDtoResponse;
import org.sqli.authentification.dto.UserFormDtoRequest;
import org.sqli.authentification.entitie.Group;
import org.sqli.authentification.entitie.User;
import org.sqli.authentification.exception.ResponseMessage;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private GroupRepository groupRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.groupRepository = groupRepository;
    }

    @Override
    public UserDtoResponse  FindUserByLoginAndPassword(UserDtoRequest userDtoRequest) {
        //1. Authentification : Best case
        User user = modelMapper.map(userDtoRequest, User.class);
        User getEntityUser = userRepository.findByLogin(user.getLogin());
        //2. Authentification : Mot de passe ou login erronés
        if (getEntityUser !=null){
            //4. Authentification : Dépassement de 3 tentatives
            if (getEntityUser.getLoginAttempts() >= 3) {

                throw new ResponseMessage("You have reached 3 failed authentication attempts, your account will be disabled");
            }

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
            }else{

                getEntityUser.setLoginAttempts(getEntityUser.getLoginAttempts() + 1);
                userRepository.save(getEntityUser);

            }

        }
        throw new ResponseMessage("Authentication error");

    }
     //5. Création de compte : Best case
    @Override
    public UserDtoResponse create(UserFormDtoRequest userFormDtoRequest) {
        Group groupUser = groupRepository.findByName(userFormDtoRequest.getGroup());
        User useEntity = modelMapper.map(userFormDtoRequest,User.class);
        useEntity.setGroup(groupUser);
        User test = useEntity;
        User save = userRepository.save(useEntity);
        return  modelMapper.map(save ,UserDtoResponse.class);
    }
}
