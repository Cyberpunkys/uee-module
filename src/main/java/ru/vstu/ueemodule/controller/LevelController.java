package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vstu.ueemodule.domain.Form;
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

    @GetMapping("{id}")
    public String levelEditPage(@PathVariable("id") Level currentLevel, Model model) {
        model.addAttribute("editLevel", currentLevel);

        return "level/editLevel";
    }

    @PutMapping("{id}")
    public String editLevel(@PathVariable("id") Level levelFromDb, @ModelAttribute("editLevel") Level editedLevel) {
        levelService.editLevel(levelFromDb, editedLevel);

        return "redirect:/levels";
    }

    @DeleteMapping("{id}")
    public String deleteLevel(@PathVariable("id") Level levelToDelete) {
        levelService.deleteLevel(levelToDelete);

        return "redirect:/levels";
    }
}
