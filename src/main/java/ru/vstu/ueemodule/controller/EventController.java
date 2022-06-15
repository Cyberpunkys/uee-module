package ru.vstu.ueemodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.domain.Event;
import ru.vstu.ueemodule.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String eventList(Model model) {
        model.addAttribute("eventList", eventService.findAll());
        model.addAttribute("newEvent", new Event());
        model.addAttribute("eventCount", eventService.count());

        return "event/listEvents";
    }

    @PostMapping
    public String createEvent(@ModelAttribute("newEvent") Event event) {
        eventService.createEvent(event);

        return "redirect:/events";
    }
}
