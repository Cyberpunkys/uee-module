package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Speciality;
import ru.vstu.ueemodule.service.SpecialityService;

@Controller
@RequestMapping("/specialities")
public class SpecialityController {

    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public String specialityList(Model model) {
        model.addAttribute("specialitiesList", specialityService.findAll());
        model.addAttribute("newSpeciality", new Speciality());
        model.addAttribute("specialityCount", specialityService.count());

        return "speciality/listSpecialities";
    }

    @PostMapping
    public String createSpeciality(@ModelAttribute("speciality") Speciality speciality) {
        specialityService.createSpeciality(speciality);

        return "redirect:/specialities";
    }
}
