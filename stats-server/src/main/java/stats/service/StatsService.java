package stats.service;

import stats.dto.HitDto;
import stats.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    HitDto saveHit(HitDto hitDto);
    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}