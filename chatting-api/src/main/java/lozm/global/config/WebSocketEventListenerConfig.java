package lozm.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

@Slf4j
@Component
public class WebSocketEventListenerConfig {

    @EventListener
    public void onSessionConnectEvent(SessionConnectEvent event) {
        log.debug(WebSocketEventListenerConfig.class.getSimpleName(), "Session Connect Event");
    }

    @EventListener
    public void onSessionConnectedEvent(SessionConnectedEvent event) {
        log.debug(WebSocketEventListenerConfig.class.getSimpleName(), "Session Connected Event");
    }

    @EventListener
    public void onSessionSubscribeEvent(SessionSubscribeEvent event) {
        log.debug(WebSocketEventListenerConfig.class.getSimpleName(), "Session Subscribe Event");
    }

    @EventListener
    public void onSessionUnsubscribeEvent(SessionUnsubscribeEvent event) {
        log.debug(WebSocketEventListenerConfig.class.getSimpleName(), "Session Unsubscribe Event");
    }

    @EventListener
    public void onSessionDisconnectEvent(SessionDisconnectEvent event) {
        log.debug(WebSocketEventListenerConfig.class.getSimpleName(), "Session Disconnect Event");
    }
}