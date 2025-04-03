package fit5120.monash.fit5120_backend.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for fetching weather information using the OpenWeather API.
 */
@Service
public class WeatherClientService {

    @Value("${openweather.api.key}")
    private String apiKey;

    /**
     * Retrieves the current weather data for a given latitude and longitude.
     *
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return A map containing weather data, including temperature and feels-like temperature,
     *         or a message/error if data retrieval fails.
     */
    public Map<String, Object> getTempByCoordinates(String lat, String lon) {
        Map<String, Object> result = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric";
            Map response = restTemplate.getForObject(url, Map.class);
            if (response == null) {
                result.put("message", "Can't get weather data for given location");
                return result;
            }
            Map main = (Map) response.get("main");
            result.put("temp", main.get("temp"));
            result.put("feels_like", main.get("feels_like"));
            return result;
        } catch (Exception e) {
            result.put("error", "Failed to retrieve weather data");
            return result;
        }
    }


    /**
     * Retrieves the current weather data for a given postcode within AU.
     *
     * @param postcode The postcode of the location.
     * @return A map containing weather data, including temperature and feels-like temperature,
     *         or an error if cannot retrieve data for invalid postcode.
     */
    public Map<String, Object> getTempByPostcode(String postcode) {
        Map<String, Object> result = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://api.openweathermap.org/geo/1.0/zip?zip=" + postcode + ",AU&appid=" + apiKey;
            Map response = restTemplate.getForObject(url, Map.class);
            System.out.println(response);
            String lat = String.valueOf(response.get("lat"));
            String lon = String.valueOf(response.get("lon"));
            String location = String.valueOf(response.get("name"));
            result = getTempByCoordinates(lat, lon);
            result.put("location", location);
            return result;
        } catch (Exception e) {
            result.put("error", "Invalid postcode");
            return result;
        }
    }
}