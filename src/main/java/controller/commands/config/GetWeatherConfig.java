package controller.commands.config;

import java.util.Map;

public class GetWeatherConfig extends FindByCoordinatesConfig {
    private String weatherKeyName;

    public String getWeatherKeyName() {
        return weatherKeyName;
    }

    public void setWeatherKeyName(String weatherKeyName) {
        this.weatherKeyName = weatherKeyName;
    }
}
