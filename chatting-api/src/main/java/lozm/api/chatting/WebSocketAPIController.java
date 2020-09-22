package lozm.api.chatting;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class WebSocketAPIController {

    private Map<String, TopicDto> topicMap = new HashMap<>();
	private final SimpMessagingTemplate template;


    @MessageMapping("/chat/in")
    public void chatIn(MessageDto messageDto) {
        checkTopicMap(messageDto.getTopic(), messageDto.getWriter());

        messageDto.setMessage("ID["+messageDto.getWriter()+"] has entered the chat room.");

        template.convertAndSend("/topic/"+messageDto.getTopic(), messageDto);
    }

    @MessageMapping("/chat/out")
    public void chatOut(MessageDto messageDto) {
        checkTopicMap(messageDto.getTopic(), messageDto.getWriter());

        messageDto.setMessage("ID["+messageDto.getWriter()+"] has left the chat room.");

        template.convertAndSend("/topic/"+messageDto.getTopic(), messageDto);
    }

    @MessageMapping("/chat/message")
    public void sendMessageToTopic(MessageDto messageDto) {
        checkTopicMap(messageDto.getTopic(), messageDto.getWriter());

		template.convertAndSend("/topic/"+messageDto.getTopic(), messageDto);
    }

    @GetMapping("/api/topic")
    public ApiResponseDto getTopicList() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, topicMap);
    }

    private void checkTopicMap(String topic, String writer) {
        TopicDto topicDto = null;
        if(topicMap.containsKey(topic)) {
            topicDto = topicMap.get(topic);
            topicDto.setEndDateTime(LocalDateTime.now());
        } else {
            topicDto = TopicDto.builder()
                    .topic(topic)
                    .creator(writer)
                    .startDateTime(LocalDateTime.now())
                    .build();
        }

        topicMap.put(topic, topicDto);
    }

}