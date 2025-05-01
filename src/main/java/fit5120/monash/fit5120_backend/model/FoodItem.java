package fit5120.monash.fit5120_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class represents a row in the food_item table in the database.
 * It contains the id, name, category, baseTemperature and avgStorageLife of the row.
 */
@Entity
@Table(name = "food_item")
public class FoodItem {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "base_temperature")
    private Double baseTemperature;

    @Column(name = "avg_storage_life")
    private Double avgStorageLife;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getBaseTemperature() {
        return baseTemperature;
    }

    public Double getAvgStorageLife() {
        return avgStorageLife;
    }
}
