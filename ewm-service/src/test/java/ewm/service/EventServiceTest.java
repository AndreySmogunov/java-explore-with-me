package ewm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ewm.dto.EventDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testCreateEvent() {
        EventDto eventDto = new EventDto(
                null,
                "Event",
                "Annotation",
                "Description",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                100,
                true,
                "PENDING",
                1L,
                1L,
                "55.751244",
                "37.618423"
        );
        EventDto savedEventDto = eventService.createEvent(eventDto);
        assertEquals(eventDto.getTitle(), savedEventDto.getTitle());
        assertEquals(eventDto.getAnnotation(), savedEventDto.getAnnotation());
        assertEquals(eventDto.getDescription(), savedEventDto.getDescription());
    }

    @Test
    public void testGetEventById() {
        EventDto eventDto = new EventDto(
                null,
                "Event",
                "Annotation",
                "Description",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                100,
                true,
                "PENDING",
                1L,
                1L,
                "55.751244",
                "37.618423"
        );
        EventDto savedEventDto = eventService.createEvent(eventDto);
        EventDto retrievedEventDto = eventService.getEventById(savedEventDto.getId());
        assertEquals(savedEventDto.getId(), retrievedEventDto.getId());
        assertEquals(savedEventDto.getTitle(), retrievedEventDto.getTitle());
    }
}