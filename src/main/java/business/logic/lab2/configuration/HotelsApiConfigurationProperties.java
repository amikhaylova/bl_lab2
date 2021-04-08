package business.logic.lab2.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("hotels-api")
@PropertySource("classpath:hotelsAPI.properties")
public class HotelsApiConfigurationProperties {
    String keyHeader;
    String keyHeaderValue;
    String hostHeader;
    String hostHeaderValue;
    String searchURL;
    String listURL;
}
