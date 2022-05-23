package ru.vstu.ueemodule.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vstu.ueemodule.domain.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Integer> {
}
