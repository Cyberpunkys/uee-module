package ru.vstu.ueemodule.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "students_groups",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id")
//    )
//    private Set<Group> groups = new HashSet<>();



//    @ManyToOne
//    @JoinColumn(name = "payment_id")
//    private Payment payment;
}
