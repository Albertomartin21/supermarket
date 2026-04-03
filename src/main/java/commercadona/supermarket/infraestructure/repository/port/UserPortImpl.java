package commercadona.supermarket.infraestructure.repository.port;

import java.util.Optional;

import org.springframework.stereotype.Service;

import commercadona.supermarket.application.mapper.AuthMapper;
import commercadona.supermarket.domain.model.User;
import commercadona.supermarket.domain.repository.UserPort;
import commercadona.supermarket.infraestructure.repository.UserRepository;

@Service
public class UserPortImpl implements UserPort{

    private UserRepository userRepository;
    private AuthMapper authMapper;

    public UserPortImpl(UserRepository userRepository, AuthMapper authMapper) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
    }
    @Override
    public User findUserByUsername(String username) {
          return userRepository.findByUsername(username)
                .map(authMapper::mapUserEntityToUser)
                .orElse(null);
        
    }
    @Override
    public User save(User user) {
          return Optional.of(user)
            .map(userDomain -> authMapper.mapUserToUserEntity(userDomain))
            .map(userEntity -> userRepository.save(userEntity))
            .map(savedUser -> authMapper.mapUserEntityToUser(savedUser)) 
            .orElse(null);
    }

    


}
