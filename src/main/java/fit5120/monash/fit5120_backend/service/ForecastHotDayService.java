package fit5120.monash.fit5120_backend.service;

import fit5120.monash.fit5120_backend.dto.HeatMapDto;
import fit5120.monash.fit5120_backend.model.ForecastHotDay;
import fit5120.monash.fit5120_backend.repository.ForecastHotDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Service class for handling forecast hot days data.
 *
 * This service is responsible for interaction with the ForecastHotDayRepository
 * and provides methods to retrieve data from the database.
 */
@Service
public class ForecastHotDayService {

    private ForecastHotDayRepository forecastHotDayRepository;

    public ForecastHotDayService(ForecastHotDayRepository forecastHotDayRepository) {
        this.forecastHotDayRepository = forecastHotDayRepository;
    }

    public List<ForecastHotDay> findAll() {
        return forecastHotDayRepository.findAll();
    }


    /**
     * Retrieves all forecast info(heatmapdto) from the database for a given year.
     */
    public List<HeatMapDto> findByYear(Integer year) {
        return forecastHotDayRepository.findByYear(year);
    }
}
