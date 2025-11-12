package stats.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.practicum.stats.StatsApplication;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = StatsApplication.class,
        properties = {
                "stats-server.url=http://localhost:8080"  // ← задаём в тестах
        }
)
public class StatsClientTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSaveHit() {
        String app = "app1";
        String uri = "/uri1";
        String ip = "192.168.1.1";
        LocalDateTime timestamp = LocalDateTime.now();

        String url = String.format("http://localhost:%d/hit", port);

        ResponseEntity<Object> response = restTemplate.postForEntity(url, Map.of(
                "app", app,
                "uri", uri,
                "ip", ip,
                "timestamp", timestamp
        ), Object.class);

        assertNotNull(response);
    }
}
