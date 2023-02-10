package ru.Savenko.view;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.Savenko.cbr.ValuteToCurrencyExchangeConverter;
import ru.Savenko.model.CurrencyExchange;
import ru.Savenko.repository.CurrencyExchangeRepositorySqliteImpl;
import ru.Savenko.utils.Export;

public class CurrencyAppController {
    private ObservableList<CurrencyExchange> currency = FXCollections.observableArrayList();
    @FXML
    private TableColumn<CurrencyExchange, Date> date;
    @FXML
    private TableColumn<CurrencyExchange, String> code;

    @FXML
    private TableColumn<CurrencyExchange, Integer> nominal;

    @FXML
    private TableColumn<CurrencyExchange, String> name;
    @FXML
    private TableColumn<CurrencyExchange, Integer> id;

    @FXML
    private TableColumn<CurrencyExchange, Double> value;

    @FXML
    private TableView table;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu help;

    @FXML
    private MenuItem CSV;

    @FXML
    private Button update;

    @FXML
    private MenuItem json;

    @FXML
    void exportCSV(ActionEvent event) {
        try {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File selectedDirectory = fileChooser.showDialog(CurrencyApp.getSt());
            Export.ExportCSV(selectedDirectory.getAbsolutePath() + "\\" + "export.csv");
        } catch (Exception e) {
            log.error("File chooser of csv was closed.");
        }

    }

    @FXML
    void exportJSON(ActionEvent event) {
        try {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File selectedDirectory = fileChooser.showDialog(CurrencyApp.getSt());
            Export.ExportJSON(selectedDirectory.getAbsolutePath() + "\\" + "export.json");
        } catch (Exception e) {
            log.error("File chooser of json was closed.");
        }
    }

    @FXML
    void printAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About project");
        alert.setHeaderText("Some info about project");
        alert.setContentText("Currency application project by Savenko");
        alert.show();
    }

    @FXML
    void update(ActionEvent event) {
        currency.clear();
        ValuteToCurrencyExchangeConverter val = new ValuteToCurrencyExchangeConverter();
        val.update();
        List<CurrencyExchange> cu = CurrencyExchangeRepositorySqliteImpl.getInstance().findAll();
        currency.addAll(cu);
        table.setItems(currency);
    }

    @FXML
    void initialize() {
        assert help != null : "fx:id=\"help\" was not injected: check your FXML file 'currency-app.fxml'.";
        assert CSV != null : "fx:id=\"CSV\" was not injected: check your FXML file 'currency-app.fxml'.";
        assert update != null : "fx:id=\"update\" was not injected: check your FXML file 'currency-app.fxml'.";
        assert json != null : "fx:id=\"json\" was not injected: check your FXML file 'currency-app.fxml'.";
        date.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, Date>("date"));
        code.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, String>("currencyCode"));
        name.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, String>("currencyName"));
        id.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, Integer>("id"));
        nominal.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, Integer>("nominal"));
        value.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, Double>("value"));
        List<CurrencyExchange> cu = CurrencyExchangeRepositorySqliteImpl.getInstance().findAll();
        currency.addAll(cu);
        table.setItems(currency);
    }

    private static final Logger log = LoggerFactory.getLogger(CurrencyAppController.class);
}
