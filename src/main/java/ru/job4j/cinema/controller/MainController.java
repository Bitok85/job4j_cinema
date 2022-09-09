package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cinema.model.FilmCell;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.TicketService;
import ru.job4j.cinema.utils.UserCheck;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    private final TicketService service;

    public MainController(TicketService service) {
        this.service = service;
    }

    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        UserCheck.checkForGuestOrExisting(model, session);
        model.addAttribute("freeCells", service.findFreeCells());
        return "main";
    }

    /**@PostMapping("/createTicket")
    public String createTicket(@RequestParam ("cell.name") String name,
                               @RequestParam ("cell.row") int row,
                               @RequestParam ("cell.cell") int cell,
                               //@RequestParam ("cell.cell") int cellId,
                               @RequestParam (name = "fail", required = false) Boolean fail,
                               HttpSession session
                               ) {
        //System.out.println(cellId);
        System.out.println(name);
        System.out.println(row);
        System.out.println(cell);
        System.out.println(session.getAttribute("user"));
        return "main";

    }*/

    @PostMapping("/createTicket")
    public String createTicket(@ModelAttribute FilmCell takenCell,
                               @RequestParam (name = "fail", required = false) Boolean fail,
                               HttpSession session) {
        System.out.println(takenCell.getName());
        System.out.println(takenCell.getRow());
        System.out.println(takenCell.getCell());
        System.out.println(takenCell.getId());
        User user = (User) session.getAttribute("user");
        System.out.println(user.getId());
        return "redirect:/main";
    }


}
