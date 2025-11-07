package ewm.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(StatsClient.class)
class StatsClientTest {

    @MockBean
    private StatsClient statsClient;

    @Test
    void testSaveHit() {
        // Данные
        String app = "app1";
        String uri = "/uri1";
        String ip = "192.168.1.1";
        LocalDateTime timestamp = LocalDateTime.now();

        // Настраиваем мок
        when(statsClient.saveHit(app, uri, ip, timestamp))
                .thenReturn(ResponseEntity.ok().build());

        // Вызов
        ResponseEntity<Object> response = statsClient.saveHit(app, uri, ip, timestamp);

        // Проверка
        assertNotNull(response);
    }
}