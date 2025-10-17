package ewm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ewm.dto.EventDto;
import ewm.model.Event;
import ewm.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setAnnotation(eventDto.getAnnotation());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setCreatedOn(eventDto.getCreatedOn());
        event.setPublishedOn(eventDto.getPublishedOn());
        event.setPaid(eventDto.getPaid());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setRequestModeration(eventDto.getRequestModeration());
        event.setState(eventDto.getState());
        event.setCategoryId(eventDto.getCategoryId());
        event.setInitiatorId(eventDto.getInitiatorId());
        event.setLocationLat(eventDto.getLocationLat());
        event.setLocationLon(eventDto.getLocationLon());
        Event savedEvent = eventRepository.save(event);
        return convertToDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(Long eventId, EventDto eventDto) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setTitle(eventDto.getTitle());
        event.setAnnotation(eventDto.getAnnotation());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setCreatedOn(eventDto.getCreatedOn());
        event.setPublishedOn(eventDto.getPublishedOn());
        event.setPaid(eventDto.getPaid());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setRequestModeration(eventDto.getRequestModeration());
        event.setState(eventDto.getState());
        event.setCategoryId(eventDto.getCategoryId());
        event.setInitiatorId(eventDto.getInitiatorId());
        event.setLocationLat(eventDto.getLocationLat());
        event.setLocationLon(eventDto.getLocationLon());
        Event updatedEvent = eventRepository.save(event);
        return convertToDto(updatedEvent);
    }

    @Override
    public EventDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        return convertToDto(event);
    }

    @Override
    public List<EventDto> getEventsByInitiatorId(Long initiatorId) {
        List<Event> events = eventRepository.findByInitiatorId(initiatorId);
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsByCategoryId(Long categoryId) {
        List<Event> events = eventRepository.findByCategoryId(categoryId);
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    private EventDto convertToDto(Event event) {
        return new EventDto(
                event.getId(),
                event.getTitle(),
                event.getAnnotation(),
                event.getDescription(),
                event.getEventDate(),
                event.getCreatedOn(),
                event.getPublishedOn(),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getRequestModeration(),
                event.getState(),
                event.getCategoryId(),
                event.getInitiatorId(),
                event.getLocationLat(),
                event.getLocationLon()
        );
    }
}