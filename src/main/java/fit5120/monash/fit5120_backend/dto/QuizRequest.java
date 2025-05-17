package fit5120.monash.fit5120_backend.dto;

/**
 * Data Transfer Object for quiz request.
 * This class encapsulates the request data for generating a packing list based on quiz input.
 * It contains information about whether the user is 65 or older, has any health issues, and the user's postcode.
 */

public class QuizRequest {
    private String is65Plus;
    private String hasHealthIssue;
    private String postcode;

    public String getIs65Plus() { return is65Plus; }
    public String getHasHealthIssue() { return hasHealthIssue; }
    public String getPostcode() { return postcode; }
}
