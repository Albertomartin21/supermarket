package commercadona.supermarket.application.usecases.impl;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.AuthMapper;
import commercadona.supermarket.application.usecases.AuthUseCase;
import commercadona.supermarket.domain.service.AuthService;
import commercadona.supermarket.infraestructure.dtos.request.UserRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.LoggerResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.UserResponseDTO;

@Service
public class AuthUseCaseImpl implements AuthUseCase {

    private AuthMapper authMapper;

    private AuthService authService;

    public AuthUseCaseImpl(AuthMapper authMapper, AuthService authService){
        this.authMapper = authMapper;
        this.authService = authService;

    }

    @Override
    public UserResponseDTO authenticateUser(UserRequestDTO userRequestDTO) {
        var response = authService.authenticateUser(authMapper.mapUserRequestDTOToUser(userRequestDTO));
        return authMapper.mapUserToUserResponseDTO(response);
    }

    @Override
    public LoggerResponseDTO registreUser(UserRequestDTO userRequestDTO) {
        var response = authService.registreUser(authMapper.mapUserRequestDTOToUser(userRequestDTO));
        return authMapper.mapLoggerToLoggerResponseDTO(response);
    }

    @Override
    public String extractUsername(String jwt) {
        return authService.extractUsername(jwt);
    }

    @Override
    public boolean isTokenValid(String jwt, String username) {
        return authService.isTokenValid(jwt, username);
    }

    @Override
    public UserResponseDTO loadUser(String username) {
         var response = authService.loadUser(username);
        return authMapper.mapUserToUserResponseDTO(response);
    }
    
}
