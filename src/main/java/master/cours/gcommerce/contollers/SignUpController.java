package master.cours.gcommerce.contollers;

import master.cours.gcommerce.entities.User;
import master.cours.gcommerce.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signup")
public class SignUpController {
    final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        String cryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptedPassword);
        return userRepository.save(user);
    }
}
