package lozm.image;

import lombok.Getter;
import lombok.Setter;
import lozm.props.CommonProps;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "image")
public class ImageProps extends CommonProps {

    private String uploadPath;
    private String defaultUserImage;

}
