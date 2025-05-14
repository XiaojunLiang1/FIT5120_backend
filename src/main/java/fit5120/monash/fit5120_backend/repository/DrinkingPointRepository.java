package fit5120.monash.fit5120_backend.repository;

import fit5120.monash.fit5120_backend.model.DrinkingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkingPointRepository extends JpaRepository<DrinkingPoint, Long> {
}
