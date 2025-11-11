package stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stats.dto.StatsDto;
import stats.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (unique) {
            return statsRepository.findUniqueStats(start, end);
        } else {
            return statsRepository.findStats(start, end);
        }
    }
}