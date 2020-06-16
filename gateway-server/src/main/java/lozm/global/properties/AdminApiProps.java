package lozm.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "admin-api")
public class AdminApiProps {

	private String url;
	private String store;
	private String coupon;
	private String item;
	private String delivery;
	private String orders;

}
