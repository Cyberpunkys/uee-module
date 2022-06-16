package ru.vstu.ueemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vstu.ueemodule.domain.Coursework;
import ru.vstu.ueemodule.domain.Student;

import java.util.List;

public interface CourseworkRepository extends JpaRepository<Coursework, Integer> {

    List<Coursework> findByStudent(Student student);
}
