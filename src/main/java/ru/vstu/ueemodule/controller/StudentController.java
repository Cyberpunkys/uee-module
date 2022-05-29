package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.service.GroupService;
import ru.vstu.ueemodule.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String studentsList(Model model) {
        model.addAttribute("studentsList", studentService.findAll());
        model.addAttribute("newStudent", new Student());
        model.addAttribute("studentsCount", studentService.count());
        model.addAttribute("groupList", groupService.findAll());

        return "students/listStudents";
    }

    @PostMapping
    public String createStudent(
            @ModelAttribute("newStudent") Student newStudent,
            @RequestParam(name = "groups") int[] groups
    ) {
        studentService.createStudent(newStudent, groups);

        return "redirect:/students";
    }
}
