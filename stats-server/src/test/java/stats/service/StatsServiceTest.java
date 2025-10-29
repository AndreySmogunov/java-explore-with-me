package stats.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stats.dto.StatsDto;
import stats.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;

    @InjectMocks
    private StatsServiceImpl statsService;

    @Test
    public void testGetStats() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<StatsDto> expectedStats = Arrays.asList(
                new StatsDto("app1", "/uri1", 10L),
                new StatsDto("app2", "/uri2", 20L)
        );

        when(statsRepository.findStats(start, end)).thenReturn(expectedStats);

        List<StatsDto> actualStats = statsService.getStats(start, end, null, false);

        assertEquals(expectedStats, actualStats);
    }

    @Test
    public void testGetUniqueStats() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<StatsDto> expectedStats = Arrays.asList(
                new StatsDto("app1", "/uri1", 5L),
                new StatsDto("app2", "/uri2", 10L)
        );

        when(statsRepository.findUniqueStats(start, end)).thenReturn(expectedStats);

        List<StatsDto> actualStats = statsService.getStats(start, end, null, true);

        assertEquals(expectedStats, actualStats);
    }
}