package commercadona.supermarket.application.mapper;

import commercadona.supermarket.domain.model.Logger;
import commercadona.supermarket.domain.model.User;
import commercadona.supermarket.infraestructure.dtos.request.UserRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.LoggerResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.UserResponseDTO;
import commercadona.supermarket.infraestructure.entity.UserEntity;

public interface AuthMapper {
    User mapUserRequestDTOToUser (UserRequestDTO userRequestDTO);

    UserResponseDTO mapUserToUserResponseDTO (User user);

    LoggerResponseDTO mapLoggerToLoggerResponseDTO (Logger logger);

    User mapUserEntityToUser(UserEntity userEntity);

    UserEntity mapUserToUserEntity(User user);
}
