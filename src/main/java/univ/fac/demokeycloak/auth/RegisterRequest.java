package univ.fac.demokeycloak.auth;

public record RegisterRequest(String username, String password, String role) {
}
