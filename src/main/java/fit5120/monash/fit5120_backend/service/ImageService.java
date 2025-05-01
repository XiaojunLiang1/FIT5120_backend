package fit5120.monash.fit5120_backend.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class ImageService {

    private static final String API_KEY = "9YguYs0AUgwqjIIqynBqmexPVIK3Dcua291n4Bjr9518sK1WetDli7IV";

    /**
     * this method is used to get image url from pexels api
     * @param keyword
     * @return
     */
    public static String getImageUrlFromPexelsApi(String keyword) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.pexels.com/v1/search?query=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8) + "&per_page=1&size=small"))
                    .header("Authorization", API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());
            JSONArray photos = json.getJSONArray("photos");
            if (photos.length() > 0) {
                return photos.getJSONObject(0).getJSONObject("src").getString("original");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
