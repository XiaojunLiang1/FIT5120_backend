package fit5120.monash.fit5120_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "forecast_extreme_hot_days")
public class ForecastHotDay {

    @Id
    public int id;
    @Column(name = "city")
    private String city;
    @Column(name = "year")
    private int year;
    @Column(name = "predicted_very_hot_days")
    private int predictedHotDays;
    @Column(name = "predicted_hot_days")
    private int PredictedVeryHotDays;

    public int getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
    public int getYear() {
        return year;
    }
    public int getPredictedHotDays() {
        return predictedHotDays;
    }
}
