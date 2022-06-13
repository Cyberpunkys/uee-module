package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registrationPage() {
        return "registration/registration";
    }

    @PostMapping
    public String registerNewUser(
            User user,
            Model model,
            @RequestParam("surname") String surname,
            @RequestParam("name") String name,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("retype") String retype
    ) {
        return userService.createUser(user, model, surname, name, patronymic, retype);
    }
}
