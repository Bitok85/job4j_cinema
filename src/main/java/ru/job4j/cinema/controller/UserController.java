package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/regUser")
    public String regUser(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "regUser";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = service.add(user);
        if (regUser.isEmpty()) {
            return "redirect:/regUser?fail=true";
        }
        return "redirect:/regUser";
    }

    @GetMapping("/login")
    public String registration(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest request) {
        Optional<User> userDb = service.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userDb.isEmpty()) {
            return "redirect:/login?fail=true";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/main";
    }


}
