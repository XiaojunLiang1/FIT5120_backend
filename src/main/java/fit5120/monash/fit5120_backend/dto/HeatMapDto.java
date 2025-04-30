package fit5120.monash.fit5120_backend.dto;

/**
 * Data Transfer Object for transferring data related to heat map.
 */

public class HeatMapDto {
    private String state;
    private Integer seasonYear;
    private Integer veryHotDays;
    private Integer hotDays;
    private String seasonStartDate;
    private String seasonEndDate;

    public HeatMapDto(String state, Integer seasonYear, Integer veryHotDays, Integer hotDays, String seasonStartDate, String seasonEndDate) {
        this.state = state;
        this.seasonYear = seasonYear;
        this.veryHotDays = veryHotDays;
        this.hotDays = hotDays;
        this.seasonStartDate = seasonStartDate;
        this.seasonEndDate = seasonEndDate;
    }

    public String getState() {
        return state;
    }

    public Integer getSeasonYear() {
        return seasonYear;
    }

    public Integer getVeryHotDays() {
        return veryHotDays;
    }

    public Integer getHotDays() {
        return hotDays;
    }

    public String getSeasonStartDate() {
        return seasonStartDate;
    }

    public String getPredictedEndDate() {
        return seasonEndDate;
    }
}
