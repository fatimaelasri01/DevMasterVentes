package master.cours.gcommerce.config;

import master.cours.gcommerce.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider(userService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //csrf c est un type d attack, avec jwt il resoudre ce probleme de csrf
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests(
                auth-> auth
                        .requestMatchers("/ventes/produits**", "/signup/**").permitAll()
                        .requestMatchers("/ventes/stats").hasRole("ADMIN")
                        .requestMatchers("/ventes/categories**").hasAnyRole("ADMIN", "USER")

                ).formLogin(Customizer.withDefaults())
                 .httpBasic(Customizer.withDefaults()
        );
        return http.build();
    }
}
