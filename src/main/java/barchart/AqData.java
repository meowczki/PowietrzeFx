package barchart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import json.airquality.AirQualityResponse;
import json.cities.CityResponse;
import json.countries.CountryResponse;
import json.countries.Results;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AqData {
    private final List<String> nazwy = Arrays.asList(new String[]{"pm10", "pm25", "no2", "o3", "co", "so2"});
    private final List<Double> normy = Arrays.asList(new Double[]{61., 37., 121., 101., 101., 7.});
    private Map<String, String> countriesMap;
    private String country;
    private String city;
    private List<json.airquality.Results> results;

    private String getResponse(String url) throws IOException {
        String json = "";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        json = response.toString();
        return json;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getCountries() {
        String jsonResponse = null;
        try {
            jsonResponse = getResponse("https://api.openaq.org/v1/countries");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CountryResponse countryResponse = gson.fromJson(jsonResponse, CountryResponse.class);
        countryResponse.getResults().removeIf(e -> e.getName() == null);
        countriesMap = countryResponse.getResults().stream().collect(Collectors.toMap(Results::getName, Results::getCode));
        return new ArrayList<String>(countriesMap.keySet());
    }

    public List<String> getCities() throws IOException {
        List<String> citiesList;
        StringBuilder sb = new StringBuilder("https://api.openaq.org/v1/cities?country=");
        sb.append(countriesMap.get(country));
        String jsonResponse = getResponse(sb.toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CityResponse cityResponse = gson.fromJson(jsonResponse, CityResponse.class);
        citiesList = cityResponse.getResults().stream().map(json.cities.Results::getName).collect(Collectors.toList());
        return citiesList;
    }

    private List<Double> getParam(String code) throws IOException {
        results = getMesurements();
        List<json.airquality.Results> resList = results.stream().filter(x -> x.getParameter().equals(code)).collect(Collectors.toList());
        List<Double> paramList = resList.stream().mapToDouble(json.airquality.Results::getValue).boxed().collect(Collectors.toList());
        if (resList.isEmpty()) {
            paramList.add(-1.);
            return paramList;
        }
        if (resList.get(0).getUnit().equals("ppm") && !(code.equals("co"))) {
            paramList.stream().map(x -> x * 1000).collect(Collectors.toList());
        } else if (resList.get(0).getUnit().equals("µg/m³") && (code.equals("co"))) {
            paramList = paramList.stream().map(x -> x / 1000).collect(Collectors.toList());
        }
        return paramList;
    }

    private List<json.airquality.Results> getMesurements() throws NullPointerException, IOException {
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append("https://api.openaq.org/v1/measurements?");
        urlBuild.append("country=");
        urlBuild.append(countriesMap.get(country));
        urlBuild.append("&city=");
        urlBuild.append(city);
        urlBuild.append("&date_from=2020-01-23T09:00:00.000Z&date_to=2020-01-23T09:00:00.000Z");
        String jsonResponse = null;
        jsonResponse = getResponse(urlBuild.toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        AirQualityResponse airQualityResponse = gson.fromJson(jsonResponse, AirQualityResponse.class);
        return airQualityResponse.getResults();
    }

    public void addToBarchart(BarChart<String, Number> barChart) {

        XYChart.Series dataSeries1 = new XYChart.Series();
        List<ParamValues> paramValues = new ArrayList<>();

        IntStream.rangeClosed(0, paramValues.size() - 1).forEach(z -> dataSeries1.getData().add(new XYChart.Data(paramValues.get(z).getCode(), paramValues.get(z).getValue())));
        XYChart.Series dataSeries2 = new XYChart.Series();

        dataSeries2.setName("normy");
        dataSeries1.setName("wynik");

        IntStream.rangeClosed(0, normy.size() - 1).forEach(z -> dataSeries2.getData().add(new XYChart.Data(nazwy.get(z), normy.get(z))));
        barChart.getData().add(dataSeries2);
        for (String s : nazwy) {
            try {
                paramValues.add(new ParamValues(s, getParam(s).stream().mapToDouble(a -> a).average().getAsDouble()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        paramValues.removeIf(e -> e.getValue() < 0);

        barChart.getData().add(dataSeries1);

//        for (XYChart.Series<String, Number> series : barChart.getData()) {
//            System.out.println("Series name: " + series.getName());
//
//            if (series.getName().equals("normy")) {
//                System.out.println("Series name: " + series.getName());
//                for (XYChart.Data data : series.getData()) {
//                    data.getNode().setStyle("-fx-bar-fill: green;");
//                }
//            }
//        }

    }
}
