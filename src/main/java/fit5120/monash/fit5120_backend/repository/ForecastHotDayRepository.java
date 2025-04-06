package fit5120.monash.fit5120_backend.repository;

import fit5120.monash.fit5120_backend.model.ForecastHotDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastHotDayRepository extends JpaRepository<ForecastHotDay, Integer> {
    List<ForecastHotDay> findByCity(String city);
    List<ForecastHotDay> findByYear(int year);
}
