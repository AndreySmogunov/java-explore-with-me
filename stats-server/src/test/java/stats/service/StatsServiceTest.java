package stats.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import ru.practicum.stats.dto.StatsDto;
import ru.practicum.stats.repository.StatsRepository;
import ru.practicum.stats.service.StatsServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Тесты для StatsServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;

    @InjectMocks
    private StatsServiceImpl statsService;

    @Test
    void shouldReturnStatsWhenGetStatsIsCalled() {
        // Given
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<StatsDto> expectedStats = Arrays.asList(
                new StatsDto("app1", "/uri1", 10L),
                new StatsDto("app2", "/uri2", 20L)
        );

        // When
        final OngoingStubbing<List<StatsDto>> listOngoingStubbing = when(statsRepository.findStats(start, end)).thenReturn(expectedStats);
        List<StatsDto> actualStats = statsService.getStats(start, end, null, false);

        // Then
        assertEquals(expectedStats, actualStats);
    }

    @Test
    void shouldReturnUniqueStatsWhenGetStatsIsCalledWithUniqueTrue() {
        // Given
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<StatsDto> expectedStats = Arrays.asList(
                new StatsDto("app1", "/uri1", 5L),
                new StatsDto("app2", "/uri2", 10L)
        );

        // When
        final OngoingStubbing<List<StatsDto>> listOngoingStubbing = when(statsRepository.findUniqueStats(start, end)).thenReturn(expectedStats);
        List<StatsDto> actualStats = statsService.getStats(start, end, null, true);

        // Then
        assertEquals(expectedStats, actualStats);
    }
}
