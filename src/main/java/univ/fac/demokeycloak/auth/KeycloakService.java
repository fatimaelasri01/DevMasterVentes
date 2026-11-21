package univ.fac.demokeycloak.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakService {

    private static final String KEYCLOAK = "http://localhost:8180";
    private final RestTemplate rt = new RestTemplate(); // creer un client rest qui va pointer sur keycloak c est le client que nous avons creer sur keycklok

    // ================= INSCRIPTION =================
    public void register(RegisterRequest req) {

        // 1. Login admin
        String token = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 2. Créer l'utilisateur
        Map<String, Object> cred = new HashMap<>();
        cred.put("type", "password");
        cred.put("value", req.password());
        cred.put("temporary", false); // nous qu on choisi le temps et non pas temporelle

        Map<String, Object> user = new HashMap<>();
        user.put("username", req.username());
        user.put("email", req.username() + "@demo.local");
        user.put("firstName", req.username());
        user.put("lastName", req.username());
        user.put("enabled", true);
        user.put("emailVerified", true);
        user.put("credentials", List.of(cred));

        ResponseEntity<Void> response = rt.postForEntity(  // definir methode post
                KEYCLOAK + "/admin/realms/demo/users", // lien utiliser par les utilisateurs pour creer les users
                new HttpEntity<>(user, headers),
                Void.class);

        // Keycloak renvoie l'adresse du nouvel utilisateur : son identifiant est la fin de cette adresse
        String userId = response.getHeaders().getLocation().getPath().replaceAll(".*/", "");

        // 3. Lire le rôle demandé (user ou admin)
        Map<?, ?> role = rt.exchange(
                KEYCLOAK + "/admin/realms/demo/roles/" + req.role(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class).getBody();

        // 4. Attribuer ce rôle à l'utilisateur
        rt.postForEntity(
                KEYCLOAK + "/admin/realms/demo/users/" + userId + "/role-mappings/realm",
                new HttpEntity<>(List.of(role), headers),
                Void.class);
    }

    // ================= CONNEXION =================
    public Map<?, ?> login(LoginRequest req) {
        String body = "grant_type=password&client_id=demo-app"
                + "&username=" + req.username() + "&password=" + req.password();
        return postForm("/realms/demo/protocol/openid-connect/token", body);
    }

    // ================= OUTILS =================
    private String getAdminToken() { // ce client juste pour acceder a keyckloak puis on suish a l autre client demo-app qui va gerer les utilisateur
        String body = "grant_type=password&client_id=admin-cli&username=admin&password=admin"; // utilisateur spring boot
        return postForm("/realms/master/protocol/openid-connect/token", body) //lien qui permet de retourner les tokens
                .get("access_token").toString(); // token de admin-cli lui meme
    }

    private Map<?, ?> postForm(String path, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return rt.postForEntity(KEYCLOAK + path, new HttpEntity<>(body, headers), Map.class).getBody();
    }
}