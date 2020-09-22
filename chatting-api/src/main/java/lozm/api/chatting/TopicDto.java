package lozm.api.chatting;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class TopicDto {

    private String topic;
    private String creator;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
