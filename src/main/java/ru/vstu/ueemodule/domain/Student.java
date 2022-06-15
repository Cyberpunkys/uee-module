package ru.vstu.ueemodule.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
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

    private String certificateFilename;

    private LocalDate injectionDate;

    @OneToMany(mappedBy = "student")
    private Set<Seat> seats = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Charter> charters;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Coursework> courseworks;

    public boolean hasExpiredVaccineNow() {
        return ChronoUnit.DAYS.between(injectionDate, LocalDate.now(ZoneId.of("Europe/Moscow"))) > 365;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
