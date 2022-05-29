package ru.vstu.ueemodule.domain.key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GroupStudentKey implements Serializable {

    @Column(name = "student_id")
    Integer studentId;

    @Column(name = "group_id")
    Integer groupId;
}
