package fit5120.monash.fit5120_backend.service;

import fit5120.monash.fit5120_backend.dto.FoodRiskDto;
import fit5120.monash.fit5120_backend.model.FoodItem;
import fit5120.monash.fit5120_backend.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service class for handling food item data
 */
@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final WeatherClientService weatherClientService;

    public FoodItemService(FoodItemRepository foodItemRepository, WeatherClientService weatherClientService) {
        this.foodItemRepository = foodItemRepository;
        this.weatherClientService = weatherClientService; ;
    }

    /**
     * returns a list of all food items
     * @return
     */
    public List<FoodItem> getAllItems() {
        return foodItemRepository.findAll();
    }

    /**
     * returns the risk of a food item base on the name and temperature
     * @param name
     * @param temperature
     */
//    public FoodRiskDto getFoodRisk(String name, double temperature) {
//        try {
//            FoodItem item = foodItemRepository.findByName(name);
//            if (item == null) return null;
//            double q10 = 2.0;
//            double baseTemp = item.getBaseTemperature();
//            double baseLife = item.getAvgStorageLife();
//            double adjustedLife = baseLife * Math.pow(q10, (baseTemp - temperature) / 10.0);
//            adjustedLife = Math.round(adjustedLife * 10.0) / 10.0;
//            String risk = "";
//            String riskDescription = "";
//
//            // risk calculation
//            if (adjustedLife < 1) {
//                risk = "Critical";
//                riskDescription = "At the current temperature, the shelf life of this food is less than 24 hours. High temperature creates an ideal environment for rapid bacterial proliferation, including harmful strains such as Salmonella, Listeria, and E. coli. \n" +
//                        "For elderly individuals with weakened immunity, consuming such food may lead to severe gastrointestinal infections, fever, or even hospitalization. \n" +
//                        "⚠\uFE0F Immediate refrigeration is essential. Discard if the food shows any signs of spoilage (odor, texture change, or discoloration).";
//            } else if (adjustedLife <= 3) {
//                risk = "High";
//                riskDescription = "Food is likely to spoil within 3 days at room temperature. Elevated temperatures significantly accelerate microbial growth and enzymatic breakdown, leading to souring, mold formation, and nutrient degradation. \n" +
//                        "For older adults, this raises the risk of foodborne illnesses, nausea, and food poisoning.\n" +
//                        "⚠\uFE0F It is strongly recommended to refrigerate the item immediately and consume soon.\n";
//            } else if (adjustedLife < 8) {
//                risk = "Moderate";
//                riskDescription = "Shelf life is reduced by more than 50% due to increased metabolic activity and microbial reproduction at this temperature. \n" +
//                        "Although the food may appear visually acceptable, taste and safety may be compromised within a few days. \n" +
//                        "For seniors, reduced digestive resilience makes them more susceptible to mild infections and nutritional imbalance.\n" +
//                        "⚠\uFE0F Store in a refrigerator (below 4°C) and consume within a week. Inspect carefully before eating.\n";
//            } else {
//                risk = "Low";
//                riskDescription = "Slightly exceeds the optimal storage condition. Shelf life is a bit shortened, but spoilage risk remains manageable for the next several days. \n" +
//                        "However, enzymatic softening and early microbial activity may begin, especially for fresh produce. \n" +
//                        "Older adults should still be cautious of reduced texture or off-flavor.\n" +
//                        "✅ Keep in a cool, dry place or refrigerate if possible. Consume within a week.";
//            }
//
//            String msg =  risk + "Risk: " + riskDescription;
//            String imageUrl = ImageService.getImageUrlFromPexelsApi(name);
//            return new FoodRiskDto(name, risk, msg, imageUrl);
//        } catch (NumberFormatException e) {
//            return null;
//        }
//    }

    /**
     * returns the risk details of a food item base on the name and current location temperature and humidity
     * @param name
     * @param lat
     * @param lon
     */
    public FoodRiskDto getCurrentFoodRisk(String name, double lat, double lon) {
        try {
            FoodItem item = foodItemRepository.findByName(name);
            if (item == null) return null;

            double temperature = 10;
            double humidity = 10;
            double q10 = 1.7;
            double[] weather = weatherClientService.getAvgDayTempAndHumidity(lat, lon);
            if (weather != null) {
                temperature = weather[0];
                humidity = weather[1];
            }
            // higher humidity, higher q10
            if(humidity > 80) {
                q10 = 2.0;
            } else if (humidity >60) {
                q10 = 1.8;
            }
            double baseTemp = item.getBaseTemperature();
            double baseLife = item.getAvgStorageLife();

            double adjustedLife = baseLife * Math.pow(q10, (baseTemp - temperature) / 10.0);
            adjustedLife = Math.round(adjustedLife * 10.0) / 10.0;

            String risk = "";
            String riskAssessment = "";
            String foodQualityImpact = "";
            String healthConsideration = "";
            String storageRecommendation = "";

            if (adjustedLife < 1) {
                risk = "Critical";
                riskAssessment = "At the current temperature, the shelf life of this food is less than 24 hours. High temperature creates an ideal environment for rapid bacterial proliferation, including harmful strains such as Salmonella, Listeria, and E. coli.";
                healthConsideration = "For elderly individuals with weakened immunity, consuming such food may lead to severe gastrointestinal infections, fever, or even hospitalization.";
                storageRecommendation = "\u26A0\uFE0F Immediate refrigeration is essential. Discard if the food shows any signs of spoilage (odor, texture change, or discoloration).";
            } else if (adjustedLife <= 3) {
                risk = "High";
                riskAssessment = "Food is likely to spoil within 3 days at room temperature. Elevated temperatures significantly accelerate microbial growth and enzymatic breakdown.";
                foodQualityImpact = "Spoilage may lead to souring, mold formation, and nutrient degradation.";
                healthConsideration = "For older adults, this raises the risk of foodborne illnesses, nausea, and food poisoning.";
                storageRecommendation = "\u26A0\uFE0F It is strongly recommended to refrigerate the item immediately and consume soon.";
            } else if (adjustedLife < 8) {
                risk = "Moderate";
                riskAssessment = "Shelf life is reduced by more than 50% due to increased metabolic activity and microbial reproduction at this temperature.";
                foodQualityImpact = "Although the food may appear visually acceptable, taste and safety may be compromised within a few days.";
                healthConsideration = "For seniors, reduced digestive resilience makes them more susceptible to mild infections and nutritional imbalance.";
                storageRecommendation = "\u26A0\uFE0F Store in a refrigerator (below 4\u00B0C) and consume within a week. Inspect carefully before eating.";
            } else {
                risk = "Low";
                riskAssessment = "Slightly exceeds the optimal storage condition. Shelf life is a bit shortened, but spoilage risk remains manageable for the next several days.";
                foodQualityImpact = "However, enzymatic softening and early microbial activity may begin, especially for fresh produce.";
                healthConsideration = "Older adults should still be cautious of reduced texture or off-flavor.";
                storageRecommendation = "\u2705 Keep in a cool, dry place or refrigerate if possible. Consume within a week.";
            }

            String imageUrl = ImageService.getImageUrlFromPexelsApi(name);
            return new FoodRiskDto(name, risk, riskAssessment, foodQualityImpact, healthConsideration, storageRecommendation, imageUrl);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
