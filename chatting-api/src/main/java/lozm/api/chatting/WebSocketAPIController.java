package lozm.api.chatting;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.api.chatting.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class WebSocketAPIController {

	private final SimpMessagingTemplate template;


    @MessageMapping("/chat/message")
    public void sendMessageToTopic(MessageDto messageDto) {
		template.convertAndSend("/topic/"+messageDto.getTopic(), messageDto);
    }

}