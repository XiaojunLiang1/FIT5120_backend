package fit5120.monash.fit5120_backend.dto;

import java.util.List;

/**
 * the data transfer object for food category
 */
public class FoodCategoryDto {
    private String category;
    private List<String> items;

    public FoodCategoryDto(String category, List<String> items) {
        this.category = category;
        this.items = items;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getItems() {
        return items;
    }
}
