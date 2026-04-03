package commercadona.supermarket.infraestructure.config;

import commercadona.supermarket.application.usecases.AuthUseCase;
import commercadona.supermarket.infraestructure.dtos.response.UserResponseDTO;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class SecurityRequestFilter extends OncePerRequestFilter {

    private AuthUseCase authUseCase;

    public SecurityRequestFilter (@Lazy AuthUseCase authUseCase){
        this.authUseCase = authUseCase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = authUseCase.extractUsername(jwt);
        }

        if (jwt != null && 
            username != null && 
            authUseCase.isTokenValid(jwt, username) 
            && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserResponseDTO userResponseDTO = authUseCase.loadUser(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userResponseDTO, null, List.of(new SimpleGrantedAuthority("USER")));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}