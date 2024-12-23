package com.example.sem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CitySearch {

    private TextField searchField;
    private ListView<String> suggestionList;
    private ObservableList<String> citySuggestions;

    public CitySearch(TextField searchField, ListView<String> suggestionList) {
        this.searchField = searchField;
        this.suggestionList = suggestionList;
        citySuggestions = FXCollections.observableArrayList();

        setupSearchListener();
    }

    private void setupSearchListener() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            citySuggestions.clear();
            if (newValue.length() > 2) {
            }
            suggestionList.setItems(citySuggestions);
        });
    }
}

