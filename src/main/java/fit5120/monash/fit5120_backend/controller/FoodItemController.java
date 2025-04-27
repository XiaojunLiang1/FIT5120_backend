package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.model.FoodItem;
import fit5120.monash.fit5120_backend.service.FoodItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/foodItems")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public List<FoodItem> getAllItemDetails() {
        return foodItemService.getAllItems();
    }
}
