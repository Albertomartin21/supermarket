package commercadona.supermarket.infraestructure.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import commercadona.supermarket.application.usecases.AuthUseCase;
import commercadona.supermarket.infraestructure.dtos.request.UserRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.LoggerResponseDTO;
import commercadona.supermarket.infraestructure.dtos.response.UserResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthUseCase authUseCase;

    private AuthController(AuthUseCase authUseCase){
        this.authUseCase = authUseCase;
    }

    @Operation(summary = "Autenticar un usuario", description = "Inicia sesión y genera un token JWT utilizando las credenciales proporcionadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public UserResponseDTO authenticateUser(@RequestBody UserRequestDTO userRequestDTO) {
        return authUseCase.authenticateUser(userRequestDTO);
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "409", description = "El usuario ya existe")
    })
    @PostMapping("/register")
    public LoggerResponseDTO registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return authUseCase.registreUser(userRequestDTO);
    }
}