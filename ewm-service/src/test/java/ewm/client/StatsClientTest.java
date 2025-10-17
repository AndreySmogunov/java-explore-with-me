package ewm.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import stats.dto.HitDto;
import stats.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StatsClientTest {

    @Autowired
    private StatsClient statsClient;

    @Test
    public void testSaveHit() {
        HitDto hitDto = new HitDto("app", "/uri", "192.168.0.1", LocalDateTime.now());
        ResponseEntity<HitDto> response = statsClient.saveHit(hitDto);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void testGetStats() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<String> uris = List.of("/uri");
        ResponseEntity<List<StatsDto>> response = (ResponseEntity<List<StatsDto>>) statsClient.getStats(start, end, uris, false);
        assertEquals(200, response.getStatusCodeValue());
    }
}