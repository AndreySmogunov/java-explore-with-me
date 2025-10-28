package stats.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import stats.dto.HitDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class StatsClientTest {

    @Autowired
    private StatsClient statsClient;

    @Test
    public void testSaveHit() {
        HitDto hitDto = new HitDto("app", "/uri", "192.168.0.1", LocalDateTime.now());
        ResponseEntity<HitDto> response = statsClient.saveHit(hitDto);
        assertEquals(201, response.getStatusCodeValue(), "Ожидается статус 201 Created");
    }

    @Test
    public void testGetStats() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<String> uris = List.of("/uri");
        ResponseEntity<List> response = statsClient.getStats(start, end, uris, false);
        assertEquals(200, response.getStatusCodeValue(), "Ожидается статус 200 OK");
    }
}