package json.airquality;

import java.util.List;

public class AirQualityResponse {
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public AirQualityResponse(List<Results> results) {
        this.results = results;
    }
}

