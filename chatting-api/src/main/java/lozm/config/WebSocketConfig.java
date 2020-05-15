package lozm.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.HashMap;

@Slf4j
@CrossOrigin
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final ChannelInterceptorConfig channelInterceptor;


    @Bean
    public WebSocketData webSocketSettingData() {
        WebSocketData instance = new WebSocketData();

        mergeTopicRemovalTime(instance);
        mergeMsgFilterMap(instance);
        mergeSystemMsgMap(instance);
        mergeAutoCompleteAllMsgMap(instance);
        mergeAutoCompletePersonalMsgMap(instance);

        return instance;
    }

    public void mergeTopicRemovalTime(WebSocketData data) {
        try {
            data.setTopicRemovalTime(
                Long.parseLong( chattingDao.selectChatSettingDetail(new ChatSettingVo( WS_SETTING.TOPIC_REMOVAL_TYPE.getCode() )).getValue() )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            data.setTopicRemovalTime(
                    Long.parseLong( WS_SETTING.TOPIC_REMOVAL_TIME.getCode() )
            );
        }
    }

    public void mergeMsgFilterMap(WebSocketData data) {
        try {
            data.setMsgFilterMap(
                setListToMap( chattingDao.selectChatSettingList(new ChatSettingVo( WS_SETTING.MSG_FILTER_TYPE.getCode() )) )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            data.setMsgFilterMap(
                    new HashMap<String, Object>()
            );
        }
    }

    public void mergeSystemMsgMap(WebSocketData data) {
        try {
            data.setSystemMsgMap(
                setListToMap( chattingDao.selectChatSettingList(new ChatSettingVo( WS_SETTING.SYSTEM_MSG_TYPE.getCode() )) )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            data.setSystemMsgMap(
                    new HashMap<String, Object>()
            );
        }
    }

    public void mergeAutoCompleteAllMsgMap(WebSocketData data) {
        try {
            data.setAutoCompleteAllMsgMap(
                setListToMap( chattingDao.selectChatSettingList(new ChatSettingVo( WS_SETTING.AUTO_COMPLETE_ALL_MSG.getCode() )) )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            data.setSystemMsgMap(
                    new HashMap<String, Object>()
            );
        }
    }

    public void mergeAutoCompletePersonalMsgMap(WebSocketData data) {
        try {
            data.setAutoCompletePersonalMsgMap(
                setListToMap( chattingDao.selectChatSettingList(new ChatSettingVo( WS_SETTING.AUTO_COMPLETE_PERSONAL_MSG.getCode() )) )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            data.setSystemMsgMap(
                    new HashMap<String, Object>()
            );
        }
    }

    private HashMap<String, Object> setListToMap(List<ChatSettingVo> list) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for(ChatSettingVo vo : list) map.put(vo.getKey(), vo.getValue());
        return map;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/lozm-chat")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//    	registry.enableSimpleBroker("/topic");
    	registry.enableStompBrokerRelay("/topic")
    	        .setRelayHost( getStringDecrypt(rabbitMqProperties.getRelayHost()) )
                .setRelayPort( Integer.parseInt( getStringDecrypt(rabbitMqProperties.getRelayPort()) ) )
                .setClientLogin( getStringDecrypt(rabbitMqProperties.getClientLogin()) )
                .setClientPasscode( getStringDecrypt(rabbitMqProperties.getClientPasscode()) );
    	
    	registry.setApplicationDestinationPrefixes("/publish");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInterceptor);
    }
    
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInterceptor);
    }
    
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
    	
    }
    
}
