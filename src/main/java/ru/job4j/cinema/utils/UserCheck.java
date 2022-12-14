package ru.job4j.cinema.utils;

import org.springframework.ui.Model;
import ru.job4j.cinema.model.User;

import javax.servlet.http.HttpSession;

public class UserCheck {

    public static void checkForGuestOrExisting(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUsername("Гость");
        }
        model.addAttribute("user", user);
    }
}
