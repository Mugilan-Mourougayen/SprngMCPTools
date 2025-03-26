package com.example.userDetail.Service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {
    private final RestClient restClient;

    @Value("${openweathermap.api.key}")
    private String openWeatherApiKey;

    public WeatherService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.openweathermap.org/data/2.5")
                .build();
    }

    @Tool
    public WeatherResponse getWeatherForLocation(String location) {
        return restClient.get()
                .uri("/weather?q={location}&appid={apiKey}&units=metric", location, openWeatherApiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(WeatherResponse.class);
    }

    public static class WeatherResponse {
        private Coord coord;
        private MainWeather main;
        private Wind wind;
        private Weather[] weather;
        private Location sys;
        private String name;

        // Getters and setters
        public Coord getCoord() { return coord; }
        public void setCoord(Coord coord) { this.coord = coord; }
        public MainWeather getMain() { return main; }
        public void setMain(MainWeather main) { this.main = main; }
        public Wind getWind() { return wind; }
        public void setWind(Wind wind) { this.wind = wind; }
        public Weather[] getWeather() { return weather; }
        public void setWeather(Weather[] weather) { this.weather = weather; }
        public Location getSys() { return sys; }
        public void setSys(Location sys) { this.sys = sys; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        // Nested classes to match OpenWeatherMap API response
        public static class Coord {
            private double lon;
            private double lat;

            public double getLon() { return lon; }
            public void setLon(double lon) { this.lon = lon; }
            public double getLat() { return lat; }
            public void setLat(double lat) { this.lat = lat; }
        }

        public static class MainWeather {
            private double temp;
            private double feels_like;
            private double temp_min;
            private double temp_max;
            private int humidity;
            private int pressure;

            public double getTemp() { return temp; }
            public void setTemp(double temp) { this.temp = temp; }
            public double getFeels_like() { return feels_like; }
            public void setFeels_like(double feels_like) { this.feels_like = feels_like; }
            public double getTemp_min() { return temp_min; }
            public void setTemp_min(double temp_min) { this.temp_min = temp_min; }
            public double getTemp_max() { return temp_max; }
            public void setTemp_max(double temp_max) { this.temp_max = temp_max; }
            public int getHumidity() { return humidity; }
            public void setHumidity(int humidity) { this.humidity = humidity; }
            public int getPressure() { return pressure; }
            public void setPressure(int pressure) { this.pressure = pressure; }
        }

        public static class Wind {
            private double speed;
            private int deg;

            public double getSpeed() { return speed; }
            public void setSpeed(double speed) { this.speed = speed; }
            public int getDeg() { return deg; }
            public void setDeg(int deg) { this.deg = deg; }
        }

        public static class Weather {
            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() { return id; }
            public void setId(int id) { this.id = id; }
            public String getMain() { return main; }
            public void setMain(String main) { this.main = main; }
            public String getDescription() { return description; }
            public void setDescription(String description) { this.description = description; }
            public String getIcon() { return icon; }
            public void setIcon(String icon) { this.icon = icon; }
        }

        public static class Location {
            private String country;

            public String getCountry() { return country; }
            public void setCountry(String country) { this.country = country; }
        }
    }
}