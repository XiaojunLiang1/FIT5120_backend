package fit5120.monash.fit5120_backend.repository;


import fit5120.monash.fit5120_backend.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * the repository for accessing and manipulating the food item data stored in the database
 */
@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {

    FoodItem findByName(String name);
}
