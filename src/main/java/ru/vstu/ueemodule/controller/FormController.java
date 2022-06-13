package ru.vstu.ueemodule.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vstu.ueemodule.domain.Form;
import ru.vstu.ueemodule.service.FormService;

@Controller
@RequestMapping("/forms")
@PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("{id}")
    public String formEditPage(@PathVariable("id") Form currentForm, Model model) {
        model.addAttribute("editForm", currentForm);

        return "form/editForm";
    }

    @PutMapping("{id}")
    public String editForm(@PathVariable("id") Integer id, @ModelAttribute("editForm") Form editedForm) {
        formService.editForm(id, editedForm);

        return "redirect:/forms";
    }

    @DeleteMapping("{id}")
    public String deleteForm(@PathVariable("id") Form formToDelete) {
        formService.deleteForm(formToDelete);

        return "redirect:/forms";
    }
}
