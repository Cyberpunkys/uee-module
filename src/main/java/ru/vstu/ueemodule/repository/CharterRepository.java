package ru.vstu.ueemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vstu.ueemodule.domain.Charter;
import ru.vstu.ueemodule.domain.Student;

import java.util.List;

public interface CharterRepository extends JpaRepository<Charter, Integer> {

    List<Charter> findByStudent(Student student);
}
