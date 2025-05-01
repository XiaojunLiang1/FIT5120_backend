package fit5120.monash.fit5120_backend.dto;

public class FoodRiskDto {
    private String name;
    private String riskLevel;
    private String message;
    private String imageUrl;

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
