package commercadona.supermarket.domain.repository;

import commercadona.supermarket.domain.model.User;

public interface UserPort {
    User findUserByUsername(String username);
    User save(User user);
}
