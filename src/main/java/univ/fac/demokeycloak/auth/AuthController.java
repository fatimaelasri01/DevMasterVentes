package univ.fac.demokeycloak.auth;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final KeycloakService keycloak;

    public AuthController(KeycloakService keycloak) {
        this.keycloak = keycloak;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest req) {
        keycloak.register(req);
        return Map.of("message", "Compte créé : " + req.username());
    }

    @PostMapping("/login")
    public Map<?, ?> login(@RequestBody LoginRequest req) {
        return keycloak.login(req);
    }
}