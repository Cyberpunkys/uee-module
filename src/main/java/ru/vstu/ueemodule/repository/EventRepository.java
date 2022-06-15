package ru.vstu.ueemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vstu.ueemodule.domain.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
