package business.logic.lab2.service;

import business.logic.lab2.configuration.HotelsApiConfigurationProperties;
import business.logic.lab2.dto.HotelDTO;
import business.logic.lab2.exceptions.CityNotFoundException;
import business.logic.lab2.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;


@Service
public class HotelsAPIService {

    @Autowired
    HotelsApiConfigurationProperties properties;

    @Autowired
    private JsonParser jsonParser;

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private String locale = "en_US";


    public HotelsAPIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .build();
        headers = new HttpHeaders();
    }

    @PostConstruct
    public void init() {
        headers.set(properties.getKeyHeader(), properties.getKeyHeaderValue());
        headers.set(properties.getHostHeader(), properties.getHostHeaderValue());
    }

    public List<HotelDTO> geHotelSuggestions(String place, Date checkIn, Date checkOut, int guests) {
        String destinationID = makeLocationSearchRequest(place);
        if (destinationID != null) {
            return makeSuggestionListRequest(destinationID, checkIn, checkOut, guests);
        } else {
            throw new CityNotFoundException("City with name " + place + "was not found.");
        }
    }

    private String makeLocationSearchRequest(String place) {
        String url =
                properties.getSearchURL() +
                        "?query=" +
                        place.replaceAll(" ", "%20") +
                        "&locale=" + locale;

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class, 1);
        if (response.getStatusCode() == HttpStatus.OK) {
            return jsonParser.getDestinationID(response.getBody());
        } else {
            return null;
        }
    }


    private List<HotelDTO> makeSuggestionListRequest(String destinationID, Date checkIn, Date checkOut, int guests) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String url =
                properties.getListURL() +
                        "?destinationId=" + destinationID +
                        "&pageNumber=1" +
                        "&checkIn=" + dateFormat.format(checkIn) +
                        "&checkOut=" + dateFormat.format(checkOut) +
                        "&pageSize=25" +
                        "&adults1=" + guests;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class, 1);
        if (response.getStatusCode() == HttpStatus.OK) {
            return jsonParser.retrieveHotels(response.getBody());
        } else {
            return null;
        }

    }

}
