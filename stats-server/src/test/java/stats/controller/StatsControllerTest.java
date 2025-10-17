package stats.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveHit() throws Exception {
        String hitDtoJson = "{\"app\":\"app\",\"uri\":\"/uri\",\"ip\":\"192.168.0.1\",\"timestamp\":\"2023-10-01T12:00:00\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/hit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hitDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetStats() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stats")
                        .param("start", "2023-10-01 00:00:00")
                        .param("end", "2023-10-02 00:00:00")
                        .param("uris", "/uri")
                        .param("unique", "false"))
                .andExpect(status().isOk());
    }
}