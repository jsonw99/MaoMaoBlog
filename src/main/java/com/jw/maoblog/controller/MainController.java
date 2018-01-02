package com.jw.maoblog.controller;

import com.jw.maoblog.domain.User;
import com.jw.maoblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * controller for the main page.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "fail to login. wrong username or password.");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * register the user.
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "rediect:/login";
    }

}
