package com.example.sem;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WeatherApp extends BorderPane {

    private TextField searchField;
    private ListView<String> suggestionList;

    private Button searchButton;
    private Label weatherInfo;

    public WeatherApp() {
        setupUI();
    }

    private void setupUI() {
        searchField = new TextField();
        suggestionList = new ListView<>();
        searchButton = new Button("Search");
        weatherInfo = new Label();

        CitySearch citySearch = new CitySearch(searchField, suggestionList);

        VBox vbox = new VBox(searchField, suggestionList, searchButton, weatherInfo);
        this.setCenter(vbox);

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String city = searchField.getText();
                try {
                    String weatherData = WeatherService.getWeather(city);
                    weatherInfo.setText(weatherData);
                } catch (CustomException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
