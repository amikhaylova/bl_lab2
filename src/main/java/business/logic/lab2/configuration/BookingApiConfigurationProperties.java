package business.logic.lab2.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("booking-api")
@PropertySource("classpath:bookingAPI.properties")
public class BookingApiConfigurationProperties {
    private String url;
    private String cancel_url;
}
