package commercadona.supermarket.domain.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import commercadona.supermarket.domain.exception.UserErrorException;
import commercadona.supermarket.domain.model.Logger;
import commercadona.supermarket.domain.model.User;
import commercadona.supermarket.domain.repository.UserPort;
import commercadona.supermarket.domain.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthServiceImpl implements AuthService{


    private AuthenticationManager authenticationManager;

    private UserPort userPort;

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;


    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    public AuthServiceImpl (@Lazy UserPort userPort, @Lazy AuthenticationManager authenticationManager, UserDetailsService userDetailsService, 
        PasswordEncoder passwordEncoder
    ){
        this.userPort = userPort;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User authenticateUser(User user) {
        var userToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(userToken);

        return User.builder()
                    .token(generateToken(authentication.getName()))
                    .build();
    }

    @Override
    public Logger registreUser(User user) {
        return Optional.ofNullable(userDetailsService.loadUserByUsername(user.getName()))
                .map(us -> Logger.builder().user(us.getUsername()).msg("El usuario ya existe.").build())
                .orElseGet(() -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return Optional.ofNullable(userPort.save(user))
                    .map(us -> Logger.builder().user(us.getName()).msg("Usuario registrado con éxito").build())
                     .orElseThrow(() -> new UserErrorException(String.format("Error al crear el usuario [%s]", user.getName())));
   
                });
        }
    
    @Override
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
     public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    @Override
    public User loadUser(String username) {
        return userPort.findUserByUsername(username);
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey());

        return builder.compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
