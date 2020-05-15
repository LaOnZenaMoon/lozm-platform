package lozm.config;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class WebSocketData {

    private long topicRemovalTime;
    private HashMap<String, Object> msgFilterMap;
    private HashMap<String, Object> systemMsgMap;
    private HashMap<String, Object> autoCompleteAllMsgMap;
    private HashMap<String, Object> autoCompletePersonalMsgMap;

}
