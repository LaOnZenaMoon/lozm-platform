package lozm.global.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbitmq.topic")
public class RabbitMqTopicProps {

    private String consultantList;
    private String consultantStatus;

}
