package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.service.WeatherClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Handles incoming HTTP requests for weather data
 */
@RestController
@RequestMapping("/api/weather")
public class WeatherClientController {

    private final WeatherClientService weatherClientService;

    public WeatherClientController(WeatherClientService weatherClientService) {
        this.weatherClientService = weatherClientService;
    }

    /**
     * Provides an endpoint to retrieve current weather information based on latitude and longitude.
     */
    @GetMapping
    public Map<String, Object> getTempByCoordinates(@RequestParam String lat, @RequestParam String lon) {
        return weatherClientService.getTempByCoordinates(lat,lon);
    }

    /**
     * Provides an endpoint to retrieve current weather information based on postcode.
     */
    @GetMapping("/postcode")
    public Map<String, Object> getTempByPostcode(@RequestParam String postcode) {
        return weatherClientService.getTempByPostcode(postcode);
    }

    /**
     * Provides an endpoint to retrieve the weather forecast information based on latitude, longitude, and the number of days.
     */
    @GetMapping("/forecast")
    public List<Map<String, Object>> getForecastTempByCoordinates(@RequestParam double lat, @RequestParam double lon, @RequestParam int cnt) {
        return weatherClientService.getForecastTempByCoordinates(lat, lon, cnt);
    }
}
