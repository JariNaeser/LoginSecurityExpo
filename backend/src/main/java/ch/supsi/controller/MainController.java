package ch.supsi.controller;

import ch.supsi.model.User;
import ch.supsi.service.UserService;
import ch.supsi.utils.JwtUtil;
import ch.supsi.utils.PasswordEncrypter;
import org.springframework.security.access.AccessDeniedException;
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
    @ResponseBody
    public List<User> getUsers(){
        return this.userService.getAll();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public Optional<User> getUser(@PathVariable int id){
        return this.userService.get(id);
    }

    @PostMapping("/user/new")
    @ResponseBody
    public User newUser(User user){
        this.userService.post(user);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody Credentials credentials) throws NoSuchAlgorithmException {
        // Check if user exists
        Optional<User> user = this.userService.findByUsername(credentials.getUsername());

        if(user.isPresent()){
            // Check if password is right
            if(user.get().getPassword().equals(PasswordEncrypter.encrypt(credentials.getPassword()))){
                // Success, generate and return new Token
                return JwtUtil.generateToken(credentials.getUsername());
            }
        }

        throw new AccessDeniedException("401 Unauthorized");
    }
}