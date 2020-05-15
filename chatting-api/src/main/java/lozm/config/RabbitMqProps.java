package lozm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMqProps {

    private SettingProps setting;
    private TopicProps topic;

    @Getter
    @Setter
    public class SettingProps {
        private String url;
        private String host;
        private int port;
        private String clientId;
        private String clientPassword;
    }

    @Getter
    @Setter
    public class TopicProps {
        private String consultantList;
        private String consultantStatus;
    }

}
