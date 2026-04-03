package commercadona.supermarket.application.usecases;

import commercadona.supermarket.infraestructure.dtos.request.UserRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.LoggerResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.UserResponseDTO;

public interface AuthUseCase {
    UserResponseDTO authenticateUser(UserRequestDTO userRequestDTO);

    LoggerResponseDTO registreUser(UserRequestDTO userRequestDTO);

    String extractUsername(String jwt);

    boolean isTokenValid(String jwt, String username);

    UserResponseDTO loadUser(String username);
}
