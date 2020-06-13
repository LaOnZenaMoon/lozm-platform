package lozm.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.global.props.ChattingProps;
import lozm.global.props.RabbitMqSettingProps;
import lozm.global.props.RabbitMqTopicProps;
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
    private final ChattingProps chattingProps;
    private final RabbitMqSettingProps rabbitMqSettingProps;
    private final RabbitMqTopicProps rabbitMqTopicProps;


    @Bean
    public WebSocketData webSocketSettingData() {
        return WebSocketData.builder()
                .topicRemovalTime(chattingProps.getTopicRemovalTime())
                .filteringMessage(new HashMap<>())
                .systemMessage(new HashMap<>())
                .build();
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
    	        .setRelayHost(rabbitMqSettingProps.getRelayHost())
                .setRelayPort(rabbitMqSettingProps.getRelayPort())
                .setClientLogin(rabbitMqSettingProps.getClientId())
                .setClientPasscode(rabbitMqSettingProps.getClientPassword());
    	
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
