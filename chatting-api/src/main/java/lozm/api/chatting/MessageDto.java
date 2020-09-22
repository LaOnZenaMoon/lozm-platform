package lozm.api.chatting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static lozm.util.DateUtils.IOS_yyyyMMdd_HHmmss;

@Getter @Setter
public class MessageDto {

    private String topic;
    private String writer;
    private String message;
    private String sendDateTime = LocalDateTime.now().format(IOS_yyyyMMdd_HHmmss);

}
