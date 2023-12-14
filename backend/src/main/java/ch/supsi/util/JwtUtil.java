package ch.supsi.util;
import ch.supsi.model.Token;
import ch.supsi.model.User;
import io.jsonwebtoken.*;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET = "bihubsi12ib3823ih";
    private static final long EXPIRATION_TIME = 86_400_000; // 1 day

    public static Token generateToken(User user) {
        // Create signature keys
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Create payload data
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", user.getRole());
        claims.put("userId", user.getId());

        // Generate token
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(claims)
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date())
                .setIssuer("LoginSecurityExpoBackend")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signatureAlgorithm, signingKey)
                .compact();

        return new Token(token);
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public static int extractId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        if(claims.containsKey("userId")){
            return (int) claims.get("userId");
        }

        return -1;
    }

    public static boolean validateToken(String token){
        try{
            // If following checks would fail, an exception would be thrown.
            // signature, malformed token, expired token, unsupported token and compact handler.
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (Exception ignored){}
        return false;
    }
}
