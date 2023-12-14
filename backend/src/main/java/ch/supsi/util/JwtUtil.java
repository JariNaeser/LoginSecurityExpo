package ch.supsi.util;
import ch.supsi.model.Token;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "bihubsi12ib3823ih";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static Token generateToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
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
