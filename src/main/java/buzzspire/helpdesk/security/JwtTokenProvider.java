package buzzspire.helpdesk.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import buzzspire.helpdesk.entities.concreates.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String SECRET_KEY = "secret";

    public String createToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(new Date(System.currentTimeMillis() + 864000000))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getEmailFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
    }

    public Long getIdFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getClaim("id")
                .asLong();
    }

    public String getRoleFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getClaim("role")
                .asString();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

