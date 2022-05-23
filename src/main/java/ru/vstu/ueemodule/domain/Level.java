package ru.vstu.ueemodule.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "level")
    private Set<Group> groups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Level)) return false;

        Level level = (Level) o;

        return id.equals(level.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
