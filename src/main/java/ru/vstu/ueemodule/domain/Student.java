package ru.vstu.ueemodule.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private String surname;

    private String patronymic;

    @OneToMany(mappedBy = "student")
    private Set<Seat> seats;
}
