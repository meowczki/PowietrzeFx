package json.airquality;

public class Results {
    private String location;
    private String parameter;
    private Date date;
    private double value;
    private String unit;
    private Coordinates coordinates;
    private String country;
    private String city;

    @Override
    public String toString() {
        return "Results{" +
                "location='" + location + '\'' +
                ", parameter='" + parameter + '\'' +
                ", date=" + date +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                ", coordinates=" + coordinates +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Results(String location, String parameter, Date date, double value, String unit, Coordinates coordinates, String country, String city) {
        this.location = location;
        this.parameter = parameter;
        this.date = date;
        this.value = value;
        this.unit = unit;
        this.coordinates = coordinates;
        this.country = country;
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
