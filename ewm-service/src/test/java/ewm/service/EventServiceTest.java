package ewm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ewm.dto.EventDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Откат изменений после каждого теста
class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    void testCreateEvent() {
        EventDto eventDto = new EventDto(
                null,
                "Test Event",
                "Short annotation",
                "Full description of the event",
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

        EventDto saved = eventService.createEvent(eventDto);

        assertNotNull(saved.getId(), "ID должно быть назначено после сохранения");
        assertEquals("Test Event", saved.getTitle());
        assertEquals("Short annotation", saved.getAnnotation());
        assertEquals(100, saved.getParticipantLimit());
    }

    @Test
    void testGetEventById() {
        // Создаём и сохраняем событие
        EventDto eventDto = new EventDto(
                null,
                "Event for Get",
                "Annotation",
                "Description",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                50,
                true,
                "PENDING",
                2L,
                2L,
                "59.916667",
                "30.333333"
        );

        EventDto saved = eventService.createEvent(eventDto);
        Long id = saved.getId();

        // Получаем событие по ID
        EventDto retrieved = eventService.getEventById(id);

        assertNotNull(retrieved, "Событие не должно быть null");
        assertEquals(id, retrieved.getId());
        assertEquals("Event for Get", retrieved.getTitle());
        assertEquals(50, retrieved.getParticipantLimit());
    }
}