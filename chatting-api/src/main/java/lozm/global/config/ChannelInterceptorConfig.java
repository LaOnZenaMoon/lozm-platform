package lozm.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChannelInterceptorConfig implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.debug(ChannelInterceptorConfig.class.getSimpleName(), "Pre-send");

        // accessor 의 유형에 따라 다른 처리 필요
        // CONNECT -> Header 에 존재하는 Unique Key 를 Repository 에 저장
        // SEND    -> destination 의 값 중 하나라도 Repository 에 존재 하면 SEND 처리
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if( accessor.getCommand() != null ) {
            switch (accessor.getCommand()) {
                case SEND:
                    break;

                case SUBSCRIBE:
                    break;

                case CONNECT:
                    break;

                case DISCONNECT:
                    break;

                default:
                    break;
            }
        }

        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.debug(ChannelInterceptorConfig.class.getSimpleName(), "Post-send");
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        log.debug(ChannelInterceptorConfig.class.getSimpleName(), "After Send Completion");
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        log.debug(ChannelInterceptorConfig.class.getSimpleName(), "Pre-Receive");
        return false;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        log.debug(ChannelInterceptorConfig.class.getSimpleName(), "Post-Receive");
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        log.debug(ChannelInterceptorConfig.class.getSimpleName(), "After Receive Completion");
    }

}
