package ru.vstu.ueemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.vstu.ueemodule.domain.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

    long countStudentsByCertificateFilenameIsNotNull();

    long countStudentsByInjectionDateAfter(LocalDate injectionDate);

    long countStudentsByInjectionDateBetween(LocalDate first, LocalDate second);

    long countStudentsByInjectionDateBefore(LocalDate injectionDate);

    List<Student> findByOrderBySurnameAsc();
}
