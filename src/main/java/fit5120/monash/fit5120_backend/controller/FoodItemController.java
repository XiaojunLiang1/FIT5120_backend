package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.dto.FoodCategoryDto;
import fit5120.monash.fit5120_backend.dto.FoodRiskDto;
import fit5120.monash.fit5120_backend.model.FoodItem;
import fit5120.monash.fit5120_backend.repository.FoodItemRepository;
import fit5120.monash.fit5120_backend.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles incoming HTTP requests for food item data.
 */
@RestController
@RequestMapping("/api/foodItems")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService, FoodItemRepository foodItemRepository) {
        this.foodItemService = foodItemService;
    }

    /**
     * returns a list of food items grouped by category.
     */
    @GetMapping
    public List<FoodCategoryDto> getFoodByCategory() {
        List<FoodItem> allItems = foodItemService.getAllItems();
        Map<String, List<String>> categoryMap = new HashMap<>();
        for (FoodItem item : allItems) {
            String category = item.getCategory();
            String name = item.getName();
            if (!categoryMap.containsKey(category)) {
                categoryMap.put(category, new ArrayList<>());
            }
            categoryMap.get(category).add(name);
        }
        List<FoodCategoryDto> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : categoryMap.entrySet()) {
            result.add(new FoodCategoryDto(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    /**
     * base on food name and temperature, calculate food risk
     * @param name
     * @param temperature
     */
    @GetMapping("/temperature")
    public ResponseEntity<FoodRiskDto> getFoodQ10Risk( @RequestParam String name, @RequestParam double temperature) {
        FoodRiskDto result = foodItemService.getFoodRisk(name, temperature);
        if (result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    /**
     * base on food name and current location coordinates, calculate food risk
     * @param name
     * @param lat
     * @param lon
     */
    @GetMapping("/risk")
    public ResponseEntity<FoodRiskDto> getCurrentFoodQ10Risk( @RequestParam String name, @RequestParam double lat, @RequestParam double lon) {
        FoodRiskDto result = foodItemService.getCurrentFoodRisk(name, lat, lon);
        if (result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

}
