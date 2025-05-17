package fit5120.monash.fit5120_backend.repository;

import fit5120.monash.fit5120_backend.model.SeifaAreaRisk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing and manipulating SeifaAreaRisk data.
 * This repository provides methods to finding a SeifaAreaRisk by its postcode.
 */
public interface SeifaAreaRiskRepository extends JpaRepository<SeifaAreaRisk, Integer> {
    Optional<SeifaAreaRisk> findByPostcode(Integer postcode);
}
