package ru.vstu.ueemodule.service;

import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        Iterable<Student> repositoryAll = studentRepository.findAll();
        List<Student> students = new ArrayList<>();
        repositoryAll.forEach(students::add);

        return students;
    }

    public Long count() {
        return studentRepository.count();
    }

    public void createStudent(Student newStudent) {
        studentRepository.save(newStudent);
    }
}
