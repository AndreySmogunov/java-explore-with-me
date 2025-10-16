package stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stats.dto.HitDto;
import stats.dto.StatsDto;
import stats.model.Hit;
import stats.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    @Override
    public HitDto saveHit(HitDto hitDto) {
        Hit hit = new Hit();
        hit.setApp(hitDto.getApp());
        hit.setUri(hitDto.getUri());
        hit.setIp(hitDto.getIp());
        hit.setTimestamp(hitDto.getTimestamp());
        Hit savedHit = statsRepository.save(hit);
        return new HitDto(savedHit.getApp(), savedHit.getUri(), savedHit.getIp(), savedHit.getTimestamp());
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (unique) {
            return statsRepository.findUniqueStats(start, end).stream()
                    .filter(statsDto -> uris == null || uris.isEmpty() || uris.contains(statsDto.getUri()))
                    .collect(Collectors.toList());
        } else {
            return statsRepository.findStats(start, end).stream()
                    .filter(statsDto -> uris == null || uris.isEmpty() || uris.contains(statsDto.getUri()))
                    .collect(Collectors.toList());
        }
    }
}