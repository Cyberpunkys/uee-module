package ru.vstu.ueemodule.service;

import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Event;
import ru.vstu.ueemodule.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Long count() {
        return eventRepository.count();
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }
}
