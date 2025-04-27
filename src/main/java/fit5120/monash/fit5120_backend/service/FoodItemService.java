package fit5120.monash.fit5120_backend.service;

import fit5120.monash.fit5120_backend.model.FoodItem;
import fit5120.monash.fit5120_backend.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public List<FoodItem> getAllItems() {
        return foodItemRepository.findAll();
    }
}
