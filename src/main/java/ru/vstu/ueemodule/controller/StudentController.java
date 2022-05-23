package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String studentsList(Model model) {
        model.addAttribute("studentsList", studentService.findAll());
        model.addAttribute("newStudent", new Student());
        model.addAttribute("studentsCount", studentService.count());

        return "students/listStudents";
    }

    @PostMapping
    public String createStudent(@ModelAttribute("newStudent") Student newStudent) {
        studentService.createStudent(newStudent);

        return "redirect:/students";
    }
}
