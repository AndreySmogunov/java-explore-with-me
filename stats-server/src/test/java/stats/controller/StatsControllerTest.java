package stats.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "stats-server.url=http://localhost:8080")
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetStats() throws Exception {
        String start = "2023-10-01 10:00:00";
        String end = "2023-10-02 10:00:00";

        mockMvc.perform(MockMvcRequestBuilders.get("/stats")
                        .param("start", start)
                        .param("end", end)
                        .param("uris", "/uri1", "/uri2")
                        .param("unique", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}