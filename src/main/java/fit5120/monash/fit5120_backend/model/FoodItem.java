package fit5120.monash.fit5120_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItem {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "room_temperature_days")
    private Integer roomTemperatureDays;

    @Column(name = "room_temperature_temperature")
    private Integer roomTemperatureTemperature;

    @Column(name = "room_temperature_tips")
    private String roomTemperatureTips;

    @Column(name = "storage_recommendations")
    private String storageRecommendations;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Integer getRoomTemperatureDays() {
        return roomTemperatureDays;
    }

    public Integer getRoomTemperatureTemperature() {
        return roomTemperatureTemperature;
    }

    public String getRoomTemperatureTips() {
        return roomTemperatureTips;
    }

    public String getStorageRecommendations() {
        return storageRecommendations;
    }
}
