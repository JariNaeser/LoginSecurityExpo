package ch.supsi.util;
import ch.supsi.model.Token;
import ch.supsi.model.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET = "bihubsi12ib3823ih";
    private static final long EXPIRATION_TIME = 86_400_000; // 1 day

    public static Token generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", user.getRole());
        claims.put("userId", user.getId());

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return new Token(token);
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
