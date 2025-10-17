package ewm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String title;
    private String annotation;
    private String description;
    private LocalDateTime eventDate;
    private LocalDateTime createdOn;
    private LocalDateTime publishedOn;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String state;
    private Long categoryId;
    private Long initiatorId;
    private String locationLat;
    private String locationLon;
}