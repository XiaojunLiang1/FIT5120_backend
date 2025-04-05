package fit5120.monash.fit5120_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            String location = String.valueOf(response.get("name"));
            result.put("location", location);
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
            result = getTempByCoordinates(lat, lon);
            return result;
        } catch (Exception e) {
            result.put("error", "Invalid postcode");
            return result;
        }
    }

    /**
     * Retrieves the forecasted weather data of given number of days for a given latitude and longitude.
     *
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @param cnt The number of days to forecast.
     * @return A list of maps containing forecasted weather data, including temperature and date.
     *         or an error if cannot retrieve data for invalid location.
     */
    public List<Map<String, Object>> getForecastTempByCoordinates(double lat, double lon, int cnt) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.openweathermap.org/data/2.5/forecast/daily?lat="+lat+"&lon="+lon+"&cnt="+cnt+"&appid="+apiKey+"&units=metric";
            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> responseList = (List<Map<String, Object>>) response.get("list");
            for (Map<String, Object> res : responseList) {
                int dt = (int)res.get("dt");
                LocalDate date = Instant.ofEpochSecond(dt).atZone(ZoneId.of("Australia/Melbourne")).toLocalDate();
                Map<String, Object> temp = (Map<String, Object>) res.get("temp");
                String temperature = String.valueOf(temp.get("day"));
                Map<String,Object> forcast = new HashMap<>();
                forcast.put("date", date);
                forcast.put("temperature", temperature);
                result.add(forcast);
            }
            return result;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("Failed to get forcast info: " + e.getMessage(), null);
            result.add(error);
            return result;
        }
    }
}