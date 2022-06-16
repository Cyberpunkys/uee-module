package ru.vstu.ueemodule.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.ueemodule.domain.Coursework;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.service.CourseworkService;

import java.io.IOException;

@Controller
@RequestMapping("/courseworks")
public class CourseworkController {

    private final CourseworkService courseworkService;

    public CourseworkController(CourseworkService courseworkService) {
        this.courseworkService = courseworkService;
    }

    @GetMapping("my")
    public String studentCourseworkList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("newCoursework", new Coursework());
        model.addAttribute("courseworkList", courseworkService.findByStudent(user.getStudent()));

        return "coursework/listStudentCourseworks";
    }

    @PostMapping
    public String createCoursework(
            @ModelAttribute("newCoursework") Coursework coursework,
            @AuthenticationPrincipal User user,
            @RequestParam("courseworkArchive") MultipartFile archive
    ) throws IOException {
        courseworkService.uploadCoursework(coursework, user, archive);

        return "redirect:/courseworks/my";
    }
}
