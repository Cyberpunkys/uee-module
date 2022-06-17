package ru.vstu.ueemodule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;
import ru.vstu.ueemodule.domain.Group;
import ru.vstu.ueemodule.domain.Seat;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.repository.GroupRepository;
import ru.vstu.ueemodule.repository.SeatRepository;
import ru.vstu.ueemodule.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final SeatRepository seatRepository;

    public StudentService(
            StudentRepository studentRepository,
            GroupRepository groupRepository,
            SeatRepository seatRepository
    ) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.seatRepository = seatRepository;
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

    public void createStudent(Student newStudent, int[] groups, boolean[] payments) {
        studentRepository.save(newStudent);

        for (int i = 0; i < groups.length; i++) {
            Group currentGroup = groupRepository.findById(groups[i]).orElseThrow(IllegalArgumentException::new);
            Seat seat = new Seat();
            seat.setStudent(newStudent);
            seat.setGroup(currentGroup);
            seat.setIsBudget(payments[i]);
            seatRepository.save(seat);
            newStudent.getSeats().add(seat);
        }
        studentRepository.save(newStudent);
    }

    public Student getOne(Integer id) {
        return studentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Group> getStudentGroups(Student student) {
        return student.getSeats().stream()
                .map(Seat::getGroup)
                .collect(Collectors.toList());
    }

    public void editStudent(Student studentFromDb, Map<String, String> form, int[] groups, Boolean[] payments) {
        Boolean[] filteredPayments = Arrays.stream(payments)
                .filter(Objects::nonNull)
                .toArray(Boolean[]::new);

        studentFromDb.setSurname(form.get("surname"));
        studentFromDb.setName(form.get("name"));
        studentFromDb.setPatronymic(form.get("patronymic"));
        Set<Seat> studentSeats = studentFromDb.getSeats();
        seatRepository.deleteAll(studentSeats);
        studentSeats.clear();

        for (int i = 0; i < groups.length; i++) {
            Group currentGroup = groupRepository.findById(groups[i]).orElseThrow(IllegalArgumentException::new);
            Seat seat = new Seat();
            seat.setStudent(studentFromDb);
            seat.setGroup(currentGroup);
            seat.setIsBudget(filteredPayments[i]);
            seatRepository.save(seat);
            studentFromDb.getSeats().add(seat);
        }
    }
}
