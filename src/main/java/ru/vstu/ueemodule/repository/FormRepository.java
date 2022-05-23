package ru.vstu.ueemodule.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vstu.ueemodule.domain.Form;

public interface FormRepository extends CrudRepository<Form, Integer> {
}
