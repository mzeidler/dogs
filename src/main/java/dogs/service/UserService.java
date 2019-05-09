package dogs.service;

import dogs.model.User;
import dogs.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getAuthenticatedUser(User user) {
        List<User> users = userRepository.findByUsername(user.getUsername());
        if (!users.isEmpty()) {
            User userDB = users.get(0);
            if (Objects.equals(userDB.getPassword(), user.getPassword())) {
                return userDB;
            }
        }
        return null;
    }

}
