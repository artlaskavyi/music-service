package com.arty.musicservice.controller;

import com.arty.musicservice.record.UserRequestDTO;
import com.arty.musicservice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
        System.out.println("Starting.");
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String email,
                         @RequestParam String password, @RequestParam String passwordcon) {
        System.out.println("Signup attempt: username=" + username + ", email=" + email);
        if (!password.equals(passwordcon)) {
            System.out.println("Passwords do not match.");
            return "signup";
        }
        userService.saveUser(new UserRequestDTO(username, email, password));
        System.out.println("Signup successful for user: " + username);
        return "redirect:/auth/login";
    }
}