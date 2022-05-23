package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Level;
import ru.vstu.ueemodule.service.LevelService;

@Controller
@RequestMapping("/levels")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public String levelsList(Model model) {
        model.addAttribute("levelsList", levelService.findAll());
        model.addAttribute("newLevel", new Level());
        model.addAttribute("levelCount", levelService.count());

        return "level/listLevels";
    }

    @PostMapping
    public String createLevel(@ModelAttribute("level") Level level) {
        levelService.createLevel(level);

        return "redirect:/levels";
    }
}
