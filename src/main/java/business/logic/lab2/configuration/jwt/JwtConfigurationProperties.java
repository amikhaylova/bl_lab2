package business.logic.lab2.configuration.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
@PropertySource("classpath:jwt.properties")
public class JwtConfigurationProperties {
    private String secret;
}
