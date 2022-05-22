package ru.vstu.ueemodule.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vstu.ueemodule.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
