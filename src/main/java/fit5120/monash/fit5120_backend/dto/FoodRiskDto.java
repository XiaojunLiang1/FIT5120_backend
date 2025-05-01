package fit5120.monash.fit5120_backend.dto;

/**
 *  the data transfer object for food risk
 */
public class FoodRiskDto {
    private String name;
    private String riskLevel;
    private String imageUrl;
    private String riskAssessment;
    private String foodQualityImpact;
    private String healthConsideration;
    private String storageRecommendation;

    /**
     * food response object, containing name, risk level, message and image url.
     * @param name
     * @param riskLevel
     * @param imageUrl
     */
    public FoodRiskDto(String name, String riskLevel, String riskAssessment, String foodQualityImpact, String healthConsideration, String storageRecommendation, String imageUrl) {
        this.name = name;
        this.riskLevel = riskLevel;
        this.riskAssessment = riskAssessment;
        this.foodQualityImpact = foodQualityImpact;
        this.healthConsideration = healthConsideration;
        this.storageRecommendation = storageRecommendation;
        this.imageUrl = imageUrl;
    }


    public String getName() {
        return name;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRiskAssessment() {
        return riskAssessment;
    }

    public String getFoodQualityImpact() {
        return foodQualityImpact;
    }

    public String getHealthConsideration() {
        return healthConsideration;
    }

    public String getStorageRecommendation() {
        return storageRecommendation;
    }
}
