package ru.vstu.ueemodule.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ueegroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @OneToMany(mappedBy = "group")
    private Set<Seat> seats;
}
