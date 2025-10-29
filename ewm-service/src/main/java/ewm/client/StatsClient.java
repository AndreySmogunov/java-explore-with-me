package ewm.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class StatsClient {
    private final RestTemplate restTemplate;

    public StatsClient(@Value("${stats.server.url}") String serverUrl, RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .build();
    }

    public ResponseEntity<Object> saveHit(String app, String uri, String ip, LocalDateTime timestamp) {
        Map<String, Object> parameters = Map.of(
                "app", app,
                "uri", uri,
                "ip", ip,
                "timestamp", timestamp
        );
        return restTemplate.postForEntity("/hit", parameters, Object.class);
    }
}