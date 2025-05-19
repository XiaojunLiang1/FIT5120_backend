package fit5120.monash.fit5120_backend.repository;

import fit5120.monash.fit5120_backend.model.DrinkingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A JPA repository for accessing and manipulating the drinking point data stored in the database.
 */
public interface DrinkingPointRepository extends JpaRepository<DrinkingPoint, Long> {
}
