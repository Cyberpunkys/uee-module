package ru.vstu.ueemodule.domain;

import lombok.Getter;
import lombok.Setter;
import ru.vstu.ueemodule.domain.key.GroupStudentKey;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Seat {

    @EmbeddedId
    GroupStudentKey id;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    private boolean isBudget;
}
