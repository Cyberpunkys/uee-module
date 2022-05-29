package ru.vstu.ueemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vstu.ueemodule.domain.Seat;
import ru.vstu.ueemodule.domain.key.GroupStudentKey;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, GroupStudentKey> {

    List<Seat> findByIdStudentId(Integer studentId);

    List<Seat> findByIdGroupId(Integer groupId);
}
