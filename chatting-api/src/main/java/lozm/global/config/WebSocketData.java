package lozm.global.config;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter @Builder
public class WebSocketData {

    private long topicRemovalTime;
    private HashMap<String, Object> filteringMessage;
    private HashMap<String, Object> systemMessage;

}
