package ch.supsi.controller;

import ch.supsi.model.Credentials;
import ch.supsi.model.User;
import ch.supsi.service.UserService;
import ch.supsi.util.JwtUtil;
import ch.supsi.util.PasswordHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/all")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public List<User> getUsers(){
        return this.userService.getAll();
    }

    @GetMapping("/user/{id}")
    @Secured("ROLE_USER")
    @ResponseBody
    public Optional<User> getUser(@PathVariable int id){
        return this.userService.get(id);
    }

    @PostMapping("/user/new")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public User newUser(User user){
        this.userService.post(user);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody Credentials credentials) throws NoSuchAlgorithmException {
        Optional<User> user = this.userService.findByUsername(credentials.getUsername());

        if(user.isPresent()){
            // Check if passwords match
            if(user.get().getPassword().equals(PasswordHelper.encrypt(credentials.getPassword(), user.get().getSalt()))){
                // Logged in successfully, generate Token
                return JwtUtil.generateToken(credentials.getUsername());
            }
        }

        throw new AccessDeniedException("401 Unauthorized");
    }
}