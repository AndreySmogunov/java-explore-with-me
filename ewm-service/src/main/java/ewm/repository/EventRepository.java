package ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ewm.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByInitiatorId(Long initiatorId);
    List<Event> findByCategoryId(Long categoryId);
}