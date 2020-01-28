package json.countries;

public class Results {
    private String code;
    private int count;
    private int locations;
    private int cities;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLocations() {
        return locations;
    }

    public void setLocations(int locations) {
        this.locations = locations;
    }

    public int getCities() {
        return cities;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Results(String code, int count, int locations, int cities, String name) {
        this.code = code;
        this.count = count;
        this.locations = locations;
        this.cities = cities;
        this.name = name;
    }
}
