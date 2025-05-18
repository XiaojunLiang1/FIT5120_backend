package fit5120.monash.fit5120_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for fetching location information using the Geoapify API.
 */
@Service
public class LocationClientService {

    @Value("${geoapify.api.key}")
    private String apiKey;

    /**
     * LocationClientService is responsible for fetching location information using the Geoapify API.
     * This service interacts with the Geoapify API to retrieve address suggestions based on input text.
     * The addresses are returned as a list of maps, each containing detailed address information.
     */
    public List<Map<String, Object>> completeAddress(String input) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.geoapify.com/v1/geocode/autocomplete?text=" + input + ",AU&apiKey=" + apiKey;
            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> responseList = (List<Map<String, Object>>) response.get("features");
            if (responseList == null || responseList.isEmpty()) {
                Map<String, Object> message = new HashMap<>();
                message.put("message", "Input too short or no matching address found");
                result.add(message);
                return result;
            }
            for(Map<String,Object> res : responseList) {
                Map<String,Object> addresses = new HashMap<>();
                Map<String,Object> properties = (Map<String, Object>) res.get("properties");
                Map<String,Object> geometry = (Map<String, Object>) res.get("geometry");
                List<Double> coordinates = (List<Double>)geometry.get("coordinates");
                String lat = coordinates.get(1).toString();
                String lon = coordinates.get(0).toString();
                addresses.put("address", properties.get("formatted").toString());
                addresses.put("lat", lat);
                addresses.put("lon", lon);
                result.add(addresses);
            };
            return result;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("Failed to get address info: " + e.getMessage(), null);
            result.add(error);
            return result;
        }
    }


    /**
     * Retrieves the location name for a given latitude and longitude.
     *
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return The location name or a message/error if data retrieval fails.
     */
    public String getLocationName(double lat, double lon) {
        String result = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.geoapify.com/v1/geocode/reverse?lat=" + lat +
                    "&lon=" + lon + "&format=json&apiKey=" + apiKey;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> features = (List<Map<String, Object>>) response.get("results");

            if (features == null || features.isEmpty()) {
                result = "No location found";
                return result;
            }
            result = (String) features.get(0).get("formatted");
            return result;
        } catch (Exception e) {
            result = "Failed to retrieve location";
            return result;
        }
    }
}
