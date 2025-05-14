package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.model.DrinkingPoint;
import fit5120.monash.fit5120_backend.repository.DrinkingPointRepository;
import fit5120.monash.fit5120_backend.service.DrinkingPointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DrinkingPointController handles incoming HTTP requests related to drinking points.
 *
 * This controller provides endpoints for retrieving information about drinking points.
 * It interacts with the DrinkingPointService to perform operations on drinking point data.
 */
@RestController
@RequestMapping("/api/drinkingpoints")
public class DrinkingPointController {

    private DrinkingPointService drinkingPointService;

    public DrinkingPointController(DrinkingPointService drinkingPointService) {
        this.drinkingPointService = drinkingPointService;
    }

    @GetMapping
    public List<DrinkingPoint> getAllDrinkingPoints() {
        return drinkingPointService.getAllDrinkingPoints();
    }
}
