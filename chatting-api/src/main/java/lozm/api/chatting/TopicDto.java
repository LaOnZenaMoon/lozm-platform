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

    //TODO
    private String title; //채팅방 제목
    private String password; //채팅방 비밀번호

}
