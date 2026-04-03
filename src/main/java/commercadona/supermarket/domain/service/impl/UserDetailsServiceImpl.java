package commercadona.supermarket.domain.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import commercadona.supermarket.domain.model.User;
import commercadona.supermarket.domain.repository.UserPort;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


     private final UserPort userPort;

    public UserDetailsServiceImpl(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(userPort.findUserByUsername(username))
                    .map(user -> getUser(user))
                    .orElse(null);
    }

    private UserDetails getUser(User user){
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getName())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
