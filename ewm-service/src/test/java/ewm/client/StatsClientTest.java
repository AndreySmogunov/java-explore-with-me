package ewm.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = stats.StatsApplication.class)
@ActiveProfiles("test")
public class StatsClientTest {

    @Autowired
    private StatsClient statsClient;

    @Test
    public void testGetStats() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<String> uris = Arrays.asList("/uri1", "/uri2");
        boolean unique = false;

        ResponseEntity<Object> response = statsClient.getStats(start, end, uris, unique);

        assertNotNull(response);
    }

    @Test
    public void testSaveHit() {
        String app = "app1";
        String uri = "/uri1";
        String ip = "192.168.1.1";
        LocalDateTime timestamp = LocalDateTime.now();

        ResponseEntity<Object> response = statsClient.saveHit(app, uri, ip, timestamp);

        assertNotNull(response);
    }
}