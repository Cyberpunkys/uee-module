package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Group;
import ru.vstu.ueemodule.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String groupsList(Model model) {
        model.addAttribute("groupList", groupService.findAll());
        model.addAttribute("newGroup", new Group());
        model.addAttribute("groupCount", groupService.count());

        return "groups/listGroups";
    }
}
