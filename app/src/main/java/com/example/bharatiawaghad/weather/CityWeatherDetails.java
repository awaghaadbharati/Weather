package com.example.bharatiawaghad.weather;

/**
 * Created by Bharati Awaghad on 14-04-2016.
 */
public class CityWeatherDetails {
    private String cityName;
    private String weatherDescription;
    private double temperature;
    private double windSpeeed;
    public CityWeatherDetails(String cityName, String weatherDescription, double temperature, double windSpeeed) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.windSpeeed = windSpeeed;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeeed(double windSpeeed) {
        this.windSpeeed = windSpeeed;
    }

    public String getCityName() {

        return cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeeed() {
        return windSpeeed;
    }
}
