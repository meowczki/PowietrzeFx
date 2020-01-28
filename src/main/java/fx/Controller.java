package fx;

import barchart.AqData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import org.controlsfx.control.textfield.TextFields;

import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    private AqData aqData;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private BarChart<String,Number> barChart;

    @FXML
    private JFXButton showBtn;

    @FXML
    private JFXComboBox<String> comboCountry;

    @FXML
    private JFXComboBox<String> comboCity;



    @FXML
    void onClickBtn(ActionEvent event) {
        barChart.getData().clear();
        aqData.setCity(comboCity.getValue());
        aqData.addToBarchart(barChart);
    }

    @FXML
    void onSelectedCountry(ActionEvent event) {
        aqData.setCountry(comboCountry.getValue());
        if (comboCity.getItems() != null) {
            comboCity.getItems().clear();
        }
        try {
            comboCity.getItems().addAll(aqData.getCities());
        } catch (IOException e) {
            e.printStackTrace();
        }
        comboCity.setEditable(true);
        TextFields.bindAutoCompletion(comboCity.getEditor(), comboCity.getItems());
    }

    public void saveToFile(String fileName,String json){
        try {
            FileWriter fileWriter=new FileWriter(fileName);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
        assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'gui.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'gui.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'gui.fxml'.";
        assert showBtn != null : "fx:id=\"showBtn\" was not injected: check your FXML file 'gui.fxml'.";
        assert comboCountry != null : "fx:id=\"comboCountry\" was not injected: check your FXML file 'gui.fxml'.";
        assert comboCity != null : "fx:id=\"comboCity\" was not injected: check your FXML file 'gui.fxml'.";
        aqData=new AqData();
        comboCountry.getItems().addAll(aqData.getCountries());
        comboCountry.setEditable(true);
        TextFields.bindAutoCompletion(comboCountry.getEditor(), comboCountry.getItems());
    }
    }

