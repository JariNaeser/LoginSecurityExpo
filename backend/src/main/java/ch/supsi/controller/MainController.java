package ch.supsi.controller;

import ch.supsi.service.SalaryService;
import ch.supsi.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private final SalaryService salaryService;
    private final UserService userService;

    public MainController(SalaryService salaryService, UserService userService) {
        this.salaryService = salaryService;
        this.userService = userService;
    }
}