package com.example.sem;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private static final String API_KEY = "2aafb915455b661af617eb155970af94";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public static String getWeather(String city) throws CustomException {
        try {
            String urlString = String.format(API_URL, city, API_KEY);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return parseWeatherResponse(response.toString());
            } else {
                throw new CustomException("Ошибка при получении данных о погоде: " + responseCode);
            }
        } catch (Exception e) {
            logger.error("Ошибка: {}", e.getMessage());
            throw new CustomException("Ошибка при получении данных о погоде");
        }
    }

    private static String parseWeatherResponse(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        String cityName = json.getString("name");
        String temperature = json.getJSONObject("main").getDouble("temp") + "°C";
        String weatherDescription = json.getJSONArray("weather").getJSONObject(0).getString("description");
        return String.format("Погода в %s: %s, температура: %s", cityName, weatherDescription, temperature);
    }
}

