package ewm.service;

import ewm.dto.EventDto;
import ewm.model.Event;

import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto);
    EventDto updateEvent(Long eventId, EventDto eventDto);
    EventDto getEventById(Long eventId);
    List<EventDto> getEventsByInitiatorId(Long initiatorId);
    List<EventDto> getEventsByCategoryId(Long categoryId);
    void deleteEvent(Long eventId);
}