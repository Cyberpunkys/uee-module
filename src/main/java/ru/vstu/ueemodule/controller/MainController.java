package ru.vstu.ueemodule.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PropertySource("classpath:main.properties")
@RequestMapping("/")
public class MainController {

    @Value("${university.name}")
    private String universityName;

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("name", universityName);

        return "main/index";
    }
}
