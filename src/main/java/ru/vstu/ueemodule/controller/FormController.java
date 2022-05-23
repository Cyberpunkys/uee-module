package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Form;
import ru.vstu.ueemodule.service.FormService;

@Controller
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public String formList(Model model) {
        model.addAttribute("formList", formService.findAll());
        model.addAttribute("newForm", new Form());
        model.addAttribute("formCount", formService.count());

        return "form/listForms";
    }

    @PostMapping
    public String createForm(@ModelAttribute("form") Form form) {
        formService.createForm(form);

        return "redirect:/forms";
    }
}
