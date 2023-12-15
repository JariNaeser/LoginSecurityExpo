package ch.supsi.controller;

import ch.supsi.model.Credentials;
import ch.supsi.model.Token;
import ch.supsi.model.User;
import ch.supsi.model.UserDTO;
import ch.supsi.service.UserService;
import ch.supsi.util.JwtUtil;
import ch.supsi.util.PasswordHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/all")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public List<UserDTO> getUsers(){
        return this.userService.getAll().stream().map(UserDTO::user2DTO).toList();
    }

    @GetMapping("/user/{id}")
    @Secured("ROLE_USER")
    @ResponseBody
    public UserDTO getUser(@PathVariable int id, @RequestHeader("Authorization") String token){

        int tokenId = JwtUtil.extractId(getTokenValue(token));

        //Verify if user is requesting its own data or data from another user
        if(tokenId != id) throw new AccessDeniedException("403 Forbidden");

        Optional<User> user = this.userService.get(id);

        if(user.isPresent()){
            return UserDTO.user2DTO(user.get());
        }

        throw new AccessDeniedException("404 Not Found");
    }

    @PostMapping("/user/new")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public UserDTO newUser(User user) throws NoSuchAlgorithmException {
        // Generate salt
        user.setSalt(PasswordHelper.getGeneratedSalt());
        // Hash password
        user.setPassword(PasswordHelper.encrypt(user.getPassword(), user.getSalt()));
        // Save new user
        this.userService.post(user);

        return UserDTO.user2DTO(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) throws NoSuchAlgorithmException {
        Optional<User> user = this.userService.findByUsername(credentials.getUsername());

        if(user.isPresent()){
            // Check if passwords match
            if(user.get().getPassword().equals(PasswordHelper.encrypt(credentials.getPassword(), user.get().getSalt()))){
                // Logged in successfully, generate Token
                return JwtUtil.generateToken(user.get());
            }
        }

        throw new AccessDeniedException("401 Unauthorized");
    }

    private String getTokenValue(String token){
        if(token.contains("Bearer ")){
            String[] splitted = token.split(" ");
            if(splitted.length == 2) return splitted[1];
        }
        return "";
    }
}