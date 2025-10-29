package stats.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveHit() throws Exception {
        String hitJson = "{\"app\":\"app1\",\"uri\":\"/uri1\",\"ip\":\"192.168.1.1\",\"timestamp\":\"2023-10-01T12:00:00\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/hit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hitJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}