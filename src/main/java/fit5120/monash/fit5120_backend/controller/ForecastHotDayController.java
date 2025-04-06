package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.model.ForecastHotDay;
import fit5120.monash.fit5120_backend.service.ForecastHotDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/forecastHotDay")
public class ForecastHotDayController {

    @Autowired
    private ForecastHotDayService forecastHotDayService;

    @GetMapping()
    public List<ForecastHotDay> getAllForecastHotDay() {
        return forecastHotDayService.findAll();
    }

    @GetMapping("/city")
    public List<ForecastHotDay> getForecastHotDayByCity(@RequestParam String city) {
        return forecastHotDayService.findByCity(city);
    }

    @GetMapping("/year")
    public List<ForecastHotDay> getForecastHotDayByYear(@RequestParam int year) {
        return forecastHotDayService.findByYear(year);
    }
}
