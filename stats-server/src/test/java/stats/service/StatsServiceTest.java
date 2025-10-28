package stats.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import stats.dto.HitDto;
import stats.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class StatsServiceTest {

    @Autowired
    private StatsService statsService;

    @Test
    public void testSaveHit() {
        HitDto hitDto = new HitDto("app", "/uri", "192.168.0.1", LocalDateTime.now());
        HitDto savedHitDto = statsService.saveHit(hitDto);
        assertEquals(hitDto.getApp(), savedHitDto.getApp(), "Название приложения должно совпадать");
        assertEquals(hitDto.getUri(), savedHitDto.getUri(), "URI должен совпадать");
        assertEquals(hitDto.getIp(), savedHitDto.getIp(), "IP-адрес должен совпадать");
    }

    @Test
    public void testGetStats() {
        // Сначала сохраним хит, чтобы получить статистику
        HitDto hitDto = new HitDto("app", "/uri", "192.168.0.1", LocalDateTime.now());
        statsService.saveHit(hitDto);

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<String> uris = List.of("/uri");
        List<StatsDto> stats = statsService.getStats(start, end, uris, false);

        assertEquals(1, stats.size(), "Ожидалась одна запись в статистике");
    }
}