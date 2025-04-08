package fit5120.monash.fit5120_backend.repository;

import fit5120.monash.fit5120_backend.dto.HeatMapDto;
import fit5120.monash.fit5120_backend.model.ForecastHotDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A JPA repository for accessing and manipulating the forecast hot day data stored in the database.
 */
@Repository
public interface ForecastHotDayRepository extends JpaRepository<ForecastHotDay, Integer> {
    @Query("SELECT new fit5120.monash.fit5120_backend.dto.HeatMapDto(state, seasonYear, veryHotDays, hotDays, predictedStartDate, predictedEndDate) " +
            "FROM ForecastHotDay WHERE seasonYear = :seasonYear")
    List<HeatMapDto> findByYear(@Param("seasonYear")int year);
}
