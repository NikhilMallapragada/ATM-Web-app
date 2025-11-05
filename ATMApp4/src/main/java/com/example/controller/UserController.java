package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // Load the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // looks for login.html in templates/
    }

    // Load the dashboard after successful login
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // looks for dashboard.html in templates/
    }
}
