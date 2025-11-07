package ewm.controller;

import ewm.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService; // ← ваш сервис, который использует JPA

    @Test
    public void testCreateEvent() throws Exception {
        String eventDtoJson = "{\"title\":\"Event\",\"annotation\":\"Annotation\",\"description\":\"Description\",\"eventDate\":\"2023-10-01T12:00:00\",\"createdOn\":\"2023-10-01T12:00:00\",\"publishedOn\":\"2023-10-01T12:00:00\",\"paid\":false,\"participantLimit\":100,\"requestModeration\":true,\"state\":\"PENDING\",\"categoryId\":1,\"initiatorId\":1,\"locationLat\":\"55.751244\",\"locationLon\":\"37.618423\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetEventById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events/1"))
                .andExpect(status().isOk());
    }
}