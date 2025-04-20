package com.batch2.artifact1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.batch2.artifact1.domain.Login;
import com.batch2.artifact1.service.LoginService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Login user = service.log(username, password);

        if (user != null) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
