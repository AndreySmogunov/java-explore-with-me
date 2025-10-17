package stats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stats.dto.StatsDto;
import stats.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Hit, Long> {
    @Query("SELECT h.app as app, h.uri as uri, COUNT(h.ip) as hits FROM Hit h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY hits DESC")
    List<StatsDto> findStats(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT h.app as app, h.uri as uri, COUNT(DISTINCT h.ip) as hits FROM Hit h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY hits DESC")
    List<StatsDto> findUniqueStats(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}