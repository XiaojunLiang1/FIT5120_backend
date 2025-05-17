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
        items.add(Map.of("name", "Sunscreen", "description", "Protects your skin from UV rays."));
        items.add(Map.of("name", "Hat", "description", "Blocks direct sunlight."));
        items.add(Map.of("name", "Water bottle", "description", "Stay hydrated throughout the day."));

        if (riskLevel == 2) {
            items.add(Map.of("name", "Cooling towel", "description", "Helps reduce core body temperature."));
        }

        if ("yes".equalsIgnoreCase(request.getIs65Plus())) {
            items.add(Map.of("name", "Electrolyte drink", "description", "Boosts hydration for older adults."));
        }

        if ("yes".equalsIgnoreCase(request.getHasHealthIssue())) {
            items.add(Map.of("name", "Medication pouch", "description", "Bring all prescribed medicine."));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("riskLevel", riskLevel);

        return ResponseEntity.ok(response);
    }

}
