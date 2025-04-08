package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.dto.HeatMapDto;
import fit5120.monash.fit5120_backend.model.ForecastHotDay;
import fit5120.monash.fit5120_backend.service.ForecastHotDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handles incoming HTTP requests for heat map data.
 */
@RestController
@RequestMapping("/api/heatmap")
public class ForecastHotDayController {

    @Autowired
    private ForecastHotDayService forecastHotDayService;

    @GetMapping()
    public List<ForecastHotDay> getAllForecastHotDay() {
        return forecastHotDayService.findAll();
    }

    /**
     * Provides an endpoint to retrieve the forecast hot days data for heatmap
     * based on the given year.
     */
    @GetMapping("/year")
    public List<HeatMapDto> getForecastHotDayByYear(@RequestParam int year) {
        return forecastHotDayService.findByYear(year);
    }
}
