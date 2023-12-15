package ch.supsi.service;

import ch.supsi.model.User;
import ch.supsi.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Service
public class JwtFilter extends OncePerRequestFilter {

    private UserService userService;

    public JwtFilter(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Extract JWT token from the request
            String jwt = extractJwtFromRequest(request);

            // Validate the JWT token
            if (jwt != null && JwtUtil.validateToken(jwt)) {
                // Extract user information from the token
                String username = JwtUtil.extractUsername(jwt);

                // Load user details from the database
                Optional<User> user = userService.findByUsername(username);

                if(user.isEmpty()) throw new RuntimeException();

                // Create authentication token
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user.get().getUsername(),
                        user.get().getPassword(),
                        AuthorityUtils.createAuthorityList(user.get().getRole().name())
                );

                // Set up the authentication context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException ex) {
            // Handle expired token exception
        } catch (Exception e) {
            // Handle other exceptions
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        // Extract the JWT token from the Authorization header
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
