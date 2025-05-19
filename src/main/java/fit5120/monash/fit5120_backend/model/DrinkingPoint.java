package fit5120.monash.fit5120_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class represents a row in the drinking_fountains table in the database.
 * It contains the id, description, latitude, longitude, photoUrl, name, address, postcode, suburb, state, category and status of the row.
 */

@Entity
@Table(name = "drinking_fountains")
public class DrinkingPoint {

    @Id
    private Long id;

    private String description;

    private Double lat;

    private Double lon;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }

    public Double getLon() { return lon; }
    public void setLon(Double lon) { this.lon = lon; }
}
