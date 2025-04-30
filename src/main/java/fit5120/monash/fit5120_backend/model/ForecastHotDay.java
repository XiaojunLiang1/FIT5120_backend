package fit5120.monash.fit5120_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class represents a row in the forecast_extreme_hot_days table in the database.
 * It contains the id, state, seasonYear, veryHotDays, hotDays, predictedStartDate and predictedEndDate of the row.
 */

@Entity
@Table(name = "historical_extreme_hot_days")
public class ForecastHotDay {

    @Id
    private Integer id;

    @Column(name = "state")
    private String state;

    @Column(name = "season_year")
    private Integer seasonYear;

    private String ds;

    @Column(name = "very_hot_days")
    private Integer veryHotDays;

    @Column(name = "hot_days")
    private Integer hotDays;

    @Column(name = "season_start_day")
    private Integer seasonStartDate;

    @Column(name = "season_end_day")
    private Integer seasonEndDate;


    //getter
    public Integer getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Integer getSeasonYear() {
        return seasonYear;
    }

    public String getDs() {
        return ds;
    }

    public Integer getVeryHotDays() {
        return veryHotDays;
    }

    public Integer getHotDays() {
        return hotDays;
    }

    public Integer getSeasonStartDate() {
        return seasonStartDate;
    }

    public Integer getSeasonEndDate() {
        return seasonEndDate;
    }
}
