package lozm.api.chatting.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class WebSocketAPIController {

	private final SimpMessagingTemplate template;

    @MessageMapping("/message")
    public void sendMessageToTopic(MessageDto messageDto) {
        log.debug("Writer: " + messageDto.getWriter() +", Message: "+messageDto.getMessage());
		template.convertAndSend("/topic/"+messageDto.getTopicId(), messageDto);
    }

    @MessageMapping
    public String getWebSocketBroadcast(String test) {
        log.debug(test);
        return "stomp-broadcast";
    }

}