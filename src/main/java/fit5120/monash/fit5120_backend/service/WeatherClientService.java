package fit5120.monash.fit5120_backend.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherClientService {

    @Value("${openweather.api.key}")
    private String apiKey;

    public Map<String, Object> getWeather(String lat, String lon) {
        Map<String, Object> result = new HashMap<>();
        try{
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat +"&lon=" + lon + "&appid=" + apiKey + "&units=metric";
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
}
