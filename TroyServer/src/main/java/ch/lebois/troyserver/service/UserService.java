package ch.lebois.troyserver.service;

import ch.lebois.troyserver.data.entity.User;
import ch.lebois.troyserver.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT Hermann
 */

@Service
public class UserService {

    private UserRepository userRepository;

    private ShaService shaService;

    public UserService(UserRepository userRepository, ShaService shaService) {
        this.userRepository = userRepository;
        this.shaService = shaService;
    }

    public void addUser(String name, String password) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setPasswordSha(shaService.encode(password));

    }

    public boolean userAllowedLogin(String user, String shaPassword) {
        for (User u : userRepository.findAll()) {
            if (u.getName().equals(user) && u.getPasswordSha().equals(shaPassword)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
