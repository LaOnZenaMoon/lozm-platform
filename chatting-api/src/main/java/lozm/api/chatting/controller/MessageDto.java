package lozm.api.chatting.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private String topicId;
    private String writer;
    private String message;

}
