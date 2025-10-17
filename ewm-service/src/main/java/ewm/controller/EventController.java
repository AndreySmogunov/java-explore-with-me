package ewm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ewm.dto.EventDto;
import ewm.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto);
    }

    @PutMapping("/{eventId}")
    public EventDto updateEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto) {
        return eventService.updateEvent(eventId, eventDto);
    }

    @GetMapping("/{eventId}")
    public EventDto getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping("/initiator/{initiatorId}")
    public List<EventDto> getEventsByInitiatorId(@PathVariable Long initiatorId) {
        return eventService.getEventsByInitiatorId(initiatorId);
    }

    @GetMapping("/category/{categoryId}")
    public List<EventDto> getEventsByCategoryId(@PathVariable Long categoryId) {
        return eventService.getEventsByCategoryId(categoryId);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }
}