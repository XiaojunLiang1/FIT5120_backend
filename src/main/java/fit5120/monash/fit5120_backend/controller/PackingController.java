package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.dto.QuizRequest;
import fit5120.monash.fit5120_backend.model.SeifaAreaRisk;
import fit5120.monash.fit5120_backend.repository.SeifaAreaRiskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/packing")
public class PackingController {
    private SeifaAreaRiskRepository seifaAreaRiskRepository;

    public PackingController(SeifaAreaRiskRepository seifaAreaRiskRepository) {
        this.seifaAreaRiskRepository = seifaAreaRiskRepository;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> generatePackingList(@RequestBody QuizRequest request) {
        int postcode = Integer.parseInt(request.getPostcode());
        Optional<SeifaAreaRisk> riskOpt = seifaAreaRiskRepository.findByPostcode(postcode);

        if (riskOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Postcode not found in risk database."));
        }

        int riskLevel = riskOpt.get().getAreaRiskLevel();
        List<Map<String, String>> items = new ArrayList<>();

        // Always include these
        items.add(Map.of("name", "Sunscreen (SPF 50+)", "description", "Essential for strong Aussie UV — reapply every 2 hours."));
        items.add(Map.of("name", "Hat", "description", "Blocks direct sunlight."));
        items.add(Map.of("name", "Water bottle", "description", "Stay hydrated throughout the day."));

        // Based on seifa Risk Level (0 = advantaged, 2 = disadvantaged)
        switch (riskLevel) {
            case 0 -> {
                items.add(Map.of("name", "Sunglasses", "description", "Protect your eyes from intense sunlight."));
                items.add(Map.of("name", "Cooling mist spray", "description", "Use occasionally to cool skin surface."));
                items.add(Map.of("name", "UV arm sleeves", "description", "Light, breathable sleeves to block UV rays."));
            }
            case 1 -> {
                items.add(Map.of("name", "Portable hand fan", "description", "Rechargeable fan to stay cool outdoors."));
                items.add(Map.of("name", "Breathable face cloth", "description", "Moisten and place on forehead or neck."));
                items.add(Map.of("name", "Loose cotton scarf", "description", "Can shield neck or wet and wrap for cooling."));

            }
            case 2 -> {
                items.add(Map.of("name", "Cooling towel", "description", "Helps reduce body heat in extreme conditions."));
                items.add(Map.of("name", "Extra bottled water", "description", "Carry backup water in case of limited access."));
                items.add(Map.of("name", "Emergency contact card", "description", "Useful when healthcare is less accessible."));
            }
        }

        // Age-related
        if ("yes".equalsIgnoreCase(request.getIs65Plus())) {
            items.add(Map.of("name", "Electrolyte drink", "description", "Maintains hydration and nutrient balance."));
            items.add(Map.of("name", "Walking cane with seat", "description", "Take breaks and reduce fall risk outdoors."));
            items.add(Map.of("name", "Prescription medication copy", "description", "List of medications in case of emergency."));
        } else {
            items.add(Map.of("name", "Energy bars", "description", "Light, portable nutrition for on-the-go heat days."));
            items.add(Map.of("name", "Insulated lunch pack", "description", "Keeps small food items or fruit cool."));
            items.add(Map.of("name", "Reusable sweatbands", "description", "Wear on wrist or forehead to stay dry."));
        }

        // Health-related
        if ("yes".equalsIgnoreCase(request.getHasHealthIssue())) {
            items.add(Map.of("name", "Medication pouch", "description", "Organize daily medication safely and accessibly."));
            items.add(Map.of("name", "Cooling gel patch", "description", "Provides quick relief for headache or fever."));
            items.add(Map.of("name", "Medical alert bracelet", "description", "Quickly informs responders of your condition."));
        } else {
            items.add(Map.of("name", "Basic first-aid kit", "description", "For minor injuries or emergencies."));
            items.add(Map.of("name", "Portable umbrella", "description", "Double use for sun and rain protection."));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("riskLevel", riskLevel);

        return ResponseEntity.ok(response);
    }

}
