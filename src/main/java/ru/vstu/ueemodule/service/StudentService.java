package ru.vstu.ueemodule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Group;
import ru.vstu.ueemodule.domain.Seat;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.repository.GroupRepository;
import ru.vstu.ueemodule.repository.SeatRepository;
import ru.vstu.ueemodule.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        log.info(Arrays.toString(groups));
        log.info(Arrays.toString(payments));
        log.info("Equality of arrays' lengths: " + (groups.length == payments.length)); // should be equal

        studentRepository.save(newStudent); // for ID creation sake

        for (int i = 0; i < groups.length; i++) {
            int groupId = groups[i];
            Group currentGroup = groupRepository.findById(groupId).orElseThrow(IllegalArgumentException::new);
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

    public List<Group> getStudentGroups(Student student) { // TODO: maybe Set<Group> ?
        return student.getSeats().stream()
                .map(Seat::getGroup)
                .collect(Collectors.toList());
    }
}
