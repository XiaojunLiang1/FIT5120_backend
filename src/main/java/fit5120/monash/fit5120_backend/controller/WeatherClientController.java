package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.service.WeatherClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/weather")
public class WeatherClientController {

    private final WeatherClientService weatherClientService;

    public WeatherClientController(WeatherClientService weatherClientService) {
        this.weatherClientService = weatherClientService;
    }

    @GetMapping
    public Map<String, Object> getWeather(@RequestParam String lat, @RequestParam String lon) {
        return weatherClientService.getWeather(lat,lon);
    }
}
