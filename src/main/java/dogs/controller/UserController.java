package dogs.controller;


import dogs.model.User;
import dogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/authenticate")
    public User authenticate(@RequestBody User user) {
        return userService.getAuthenticatedUser(user);
    }

}
