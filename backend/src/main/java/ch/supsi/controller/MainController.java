package ch.supsi.controller;

import ch.supsi.model.Salary;
import ch.supsi.model.User;
import ch.supsi.service.SalaryService;
import ch.supsi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final SalaryService salaryService;
    private final UserService userService;

    public MainController(SalaryService salaryService, UserService userService) {
        this.salaryService = salaryService;
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

    @PostMapping("/salary/new")
    @ResponseBody
    public Salary newSalary(Salary salary){
        this.salaryService.post(salary);
        return salary;
    }

    @PostMapping("/user/new")
    @ResponseBody
    public User newUser(User user){
        this.userService.post(user);
        return user;
    }
}