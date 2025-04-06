package fit5120.monash.fit5120_backend.service;

import fit5120.monash.fit5120_backend.model.ForecastHotDay;
import fit5120.monash.fit5120_backend.repository.ForecastHotDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastHotDayService {

    @Autowired
    private ForecastHotDayRepository forecastHotDayRepository;

    public List<ForecastHotDay> findAll() {
        return forecastHotDayRepository.findAll();
    }

    public List<ForecastHotDay> findByCity(String city) {
        return forecastHotDayRepository.findByCity(city);
    }

    public List<ForecastHotDay> findByYear(int year) {
        return forecastHotDayRepository.findByYear(year);
    }
}
