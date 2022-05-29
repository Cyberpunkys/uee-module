package ru.vstu.ueemodule.domain;

import lombok.*;
import ru.vstu.ueemodule.domain.key.GroupStudentKey;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @EmbeddedId
    GroupStudentKey id = new GroupStudentKey();

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    private Boolean isBudget;
}
