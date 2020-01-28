package json.cities;

public class Results {

    private String name;
    private String country;
    private String city;
    private String count;//liczba pomiarów
    private String locations;//liczba lokalizacji w mieście

    @Override
    public String toString() {
        return "Results{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", count='" + count + '\'' +
                ", locations='" + locations + '\'' +
                '}';
    }

    /**
     *
     * @return name of the city
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name of the city
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return country containing city, in 2 letter ISO code
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country containing city, in 2 letter ISO code
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     *
     * @return  name of the city (DEPRECATED: use "name" instead)
     */
    public String getCity() {
        return city;
    }
    /**
     *
     * @param city name of the city (DEPRECATED: use "name" instead)
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     *
     * @return  number of measurements for this city
     */
    public String getCount() {
        return count;
    }
    /**
     *
     * @param  count number of measurements for this city
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     *
     * @return Number of locations in this city
     */
    public String getLocations() {
        return locations;
    }

    /**
     *
     * @param locations Number of locations in this city
     */
    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Results(String name, String country, String city, String count, String locations) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.count = count;
        this.locations = locations;
    }
}
