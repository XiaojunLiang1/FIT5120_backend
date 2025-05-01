package fit5120.monash.fit5120_backend.dto;

/**
 *  the data transfer object for food risk
 */
public class FoodRiskDto {
    private String name;
    private String riskLevel;
    private String message;
    private String imageUrl;

    /**
     * food response object, containing name, risk level, message and image url.
     * @param name
     * @param riskLevel
     * @param message
     * @param imageUrl
     */
    public FoodRiskDto(String name, String riskLevel, String message, String imageUrl) {
        this.name = name;
        this.riskLevel = riskLevel;
        this.message = message;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public String getMessage() {
        return message;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
