package univ.fac.demokeycloak.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/public")
    public String publicUrl() {
        return "public : ouvert à tous";
    }

    @GetMapping("/me")
    public String me(Authentication auth) {
        return auth.getName() + " " + auth.getAuthorities();
    }

    @GetMapping("/user/hello")
    public String userUrl(Authentication auth) {
        return "zone user ou admin : " + auth.getName();
    }

    @GetMapping("/admin/hello")
    public String adminUrl(Authentication auth) {
        return "zone admin : " + auth.getName();
    }
}