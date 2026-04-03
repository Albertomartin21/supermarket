package commercadona.supermarket.domain.service;

import commercadona.supermarket.domain.model.Logger;
import commercadona.supermarket.domain.model.User;

public interface AuthService {
    User authenticateUser(User user);

    Logger registreUser(User user);

    String extractUsername(String jwt);

    boolean isTokenValid(String jwt, String username);

    User loadUser(String username);
}
