package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Group;
import ru.vstu.ueemodule.service.FormService;
import ru.vstu.ueemodule.service.GroupService;
import ru.vstu.ueemodule.service.LevelService;
import ru.vstu.ueemodule.service.SpecialityService;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final FormService formService;
    private final SpecialityService specialityService;
    private final LevelService levelService;

    public GroupController(
            GroupService groupService,
            FormService formService,
            SpecialityService specialityService,
            LevelService levelService
    ) {
        this.groupService = groupService;
        this.formService = formService;
        this.specialityService = specialityService;
        this.levelService = levelService;
    }

    @GetMapping
    public String groupsList(Model model) {
        model.addAttribute("groupList", groupService.findAll());
        model.addAttribute("newGroup", new Group());
        model.addAttribute("groupCount", groupService.count());
        model.addAttribute("formList", formService.findAll());
        model.addAttribute("specialityList", specialityService.findAll());
        model.addAttribute("levelList", levelService.findAll());

        return "groups/listGroups";
    }

    @PostMapping
    public String createGroup(@ModelAttribute("newGroup") Group newGroup) {
        groupService.createGroup(newGroup);

        return "redirect:/groups";
    }
}
