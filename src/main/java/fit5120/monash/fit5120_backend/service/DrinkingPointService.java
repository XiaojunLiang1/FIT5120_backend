package fit5120.monash.fit5120_backend.service;

import fit5120.monash.fit5120_backend.model.DrinkingPoint;
import fit5120.monash.fit5120_backend.model.News;
import fit5120.monash.fit5120_backend.repository.DrinkingPointRepository;
import fit5120.monash.fit5120_backend.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkingPointService {
    private DrinkingPointRepository drinkingPointRepository;

    public DrinkingPointService(DrinkingPointRepository drinkingPointRepository) {
        this.drinkingPointRepository = drinkingPointRepository;
    }

    /**
     * returns all drinking fountain from the database
     * @return
     */
    public List<DrinkingPoint> getAllDrinkingPoints() {
        return drinkingPointRepository.findAll ();
    }
}
