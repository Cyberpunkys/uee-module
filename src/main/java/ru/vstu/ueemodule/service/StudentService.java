package ru.vstu.ueemodule.service;

import org.springframework.stereotype.Service;
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
        return studentRepository.findAll();
    }

    public Long count() {
        return studentRepository.count();
    }

    public void createStudent(Student newStudent, int[] groups, Boolean[] payments) {
        studentRepository.save(newStudent);

        associateStudentWithGroups(newStudent, groups, payments);

        studentRepository.save(newStudent);
    }

    private void associateStudentWithGroups(Student newStudent, int[] groups, Boolean[] payments) {
        for (int i = 0; i < groups.length; i++) {
            Group currentGroup = groupRepository.findById(groups[i]).orElseThrow(IllegalArgumentException::new);
            Seat seat = new Seat();
            seat.setStudent(newStudent);
            seat.setGroup(currentGroup);
            seat.setIsBudget(payments[i]);
            seatRepository.save(seat);
            newStudent.getSeats().add(seat);
        }
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

        associateStudentWithGroups(studentFromDb, groups, filteredPayments);
    }

    public List<Student> findAllOrderBySurname() {
        return studentRepository.findByOrderBySurnameAsc();
    }
}
