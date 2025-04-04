package fit5120.monash.fit5120_backend.controller;

import fit5120.monash.fit5120_backend.service.LocationClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Handles incoming HTTP requests for location data.
 */
@RestController
@RequestMapping("/api/location")
public class LocationClientController {
    private final LocationClientService locationClientService;

    public LocationClientController(LocationClientService locationClientService) {
        this.locationClientService = locationClientService;
    }

    /**
     * Returns a list of maps containing the autocomplete results for the given text.
     * @param text the text to autocomplete
     * @return a list of maps containing the autocomplete results
     */
    @GetMapping
    public List<Map<String, Object>> completeAddress(@RequestParam String text) {
        return locationClientService.completeAddress(text);
    }
}
