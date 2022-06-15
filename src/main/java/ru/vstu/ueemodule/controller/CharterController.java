package ru.vstu.ueemodule.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Charter;
import ru.vstu.ueemodule.domain.CharterCategory;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.service.CharterService;
import ru.vstu.ueemodule.service.EventService;

@Controller
@RequestMapping("/charters")
public class CharterController {

    private final CharterService charterService;

    private final EventService eventService;

    public CharterController(CharterService charterService, EventService eventService) {
        this.charterService = charterService;
        this.eventService = eventService;
    }

    @GetMapping("my")
    public String charterList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("charterList", charterService.findByStudent(user.getStudent()));
        model.addAttribute("newCharter", new Charter());
        model.addAttribute("categoryList", CharterCategory.values());
        model.addAttribute("eventList", eventService.findAll());

        return "charter/listStudentCharters";
    }

    @PostMapping
    public String createCharter(@AuthenticationPrincipal User user, @ModelAttribute("newCharter") Charter charter) {
        charterService.createCharter(charter, user);

        return "redirect:/charters/my";
    }
}
