package commercadona.supermarket.application.mapper.impl;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.AuthMapper;
import commercadona.supermarket.domain.model.Logger;
import commercadona.supermarket.domain.model.User;
import commercadona.supermarket.infraestructure.dtos.request.UserRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.LoggerResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.UserResponseDTO;
import commercadona.supermarket.infraestructure.entity.UserEntity;

@Service
public class AuthMapperImpl implements AuthMapper {

    @Override
    public User mapUserRequestDTOToUser(UserRequestDTO userRequestDTO) {
        return User.builder()
        .name(userRequestDTO.getUsername())
        .password(userRequestDTO.getPassword())
        .build();
    }

    @Override
    public UserResponseDTO mapUserToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
        .token(user.getToken())
        .build();
    }

    @Override
    public LoggerResponseDTO mapLoggerToLoggerResponseDTO(Logger logger) {
       return LoggerResponseDTO.builder()
        .user(logger.getUser())
        .msg(logger.getMsg())
        .build();
    }

    @Override
    public User mapUserEntityToUser(UserEntity userEntity) {
         return User.builder()
        .name(userEntity.getUsername())
        .password(userEntity.getPassword())
        .build();
    }

    @Override
    public UserEntity mapUserToUserEntity(User user) {
        return UserEntity.builder()
        .username(user.getName())
        .password(user.getPassword())
        .build();
    }
    
}
