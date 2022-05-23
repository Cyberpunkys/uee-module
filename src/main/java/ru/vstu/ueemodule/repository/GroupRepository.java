package ru.vstu.ueemodule.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vstu.ueemodule.domain.Group;

public interface GroupRepository extends CrudRepository<Group, Integer> {
}
