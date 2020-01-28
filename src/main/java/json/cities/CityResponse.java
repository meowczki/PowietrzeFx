package json.cities;


import java.util.List;

public class CityResponse {


    private List<Results> results;

    public CityResponse(List<Results> cityRes) {
        this.results = cityRes;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
