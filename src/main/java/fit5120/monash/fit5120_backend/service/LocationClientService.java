package fit5120.monash.fit5120_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationClientService {

    @Value("${geoapify.api.key}")
    private String apiKey;

    public List<Map<String, Object>> completeAddress(String input) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.geoapify.com/v1/geocode/autocomplete?text=" + input + ",AU&apiKey=" + apiKey;
            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> responseList = (List<Map<String, Object>>) response.get("features");
            System.out.println(responseList);
            Map<String,Object> addresses = new HashMap<>();
            for(Map<String,Object> res : responseList) {
                Map<String,Object> properties = (Map<String, Object>) res.get("properties");
                Map<String,Object> geometry = (Map<String, Object>) res.get("geometry");
                List<Double> coordinates = (List<Double>)geometry.get("coordinates");
                String lat = coordinates.get(0).toString();
                String lon = coordinates.get(1).toString();
                addresses.put("address", properties.get("formatted").toString());
                addresses.put("lat", lat);
                addresses.put("lon", lon);
                result.add(addresses);
            };
            return result;
        } catch (Exception e) {
            //result.add(e.getMessage(), "Invalid postcode");
            return result;
        }
    }
}
