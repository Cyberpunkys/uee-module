package ru.vstu.ueemodule.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vstu.ueemodule.domain.Level;

public interface LevelRepository extends CrudRepository<Level, Integer> {
}
