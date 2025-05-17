package fit5120.monash.fit5120_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class represents a row in the seifa_decile_classified table in the database.
 * It contains the postcode and areaRiskLevel of the row.
 */

@Entity
@Table(name = "seifa_decile_classified")
public class SeifaAreaRisk {

    @Id
    private Integer postcode;

    @Column(name = "area_risk_level")
    private Integer areaRiskLevel;

    // Getter
    public Integer getPostcode() {
        return postcode;
    }

    public Integer getAreaRiskLevel() {
        return areaRiskLevel;
    }
}
