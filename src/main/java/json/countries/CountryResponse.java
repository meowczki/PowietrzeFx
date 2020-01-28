package json.countries;


import java.util.List;

public class CountryResponse {
    private List<Results> results;


    public CountryResponse(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

}

