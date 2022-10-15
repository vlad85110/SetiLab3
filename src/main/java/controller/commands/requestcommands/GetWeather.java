package controller.commands.requestcommands;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.commands.config.CommandConfig;
import controller.commands.config.GetWeatherConfig;
import exceptions.command.CommandException;
import exceptions.view.ViewException;
import model.Data;
import model.Point;
import model.Weather;
import org.json.JSONObject;
import parser.ConfigParser;
import view.View;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class GetWeather extends FindByCoordinatesCommand {
    public GetWeather(ConfigParser configParser, HttpClient client, Point point) {
        super(configParser, client);
        this.point = point;
    }

    @Override
    protected Class<? extends CommandConfig> getConfigClass() {
        return GetWeatherConfig.class;
    }

    @Override
    protected void handleResponse(HttpResponse<String> response, Data data, View view,
                                  CommandConfig config) throws CommandException {
        GetWeatherConfig getWeatherConfig = (GetWeatherConfig)config;
        var weatherKeyName = getWeatherConfig.getWeatherKeyName();

        var weather = createWeatherFromResponse(response, weatherKeyName);
        data.addWeather(point, weather);

        try {
            view.updateWeather(weather);
        } catch (ViewException e) {
            throw new CommandException(e);
        }
    }

    private Weather createWeatherFromResponse(HttpResponse<String> response, String weatherKeyName)
            throws CommandException {
        JSONObject object = new JSONObject(response.body());
        JSONObject weatherResponse = object.getJSONObject(weatherKeyName);

        try {
            return mapper.readValue(weatherResponse.toString(), Weather.class);
        } catch (JsonProcessingException e) {
            throw new CommandException(e);
        }
    }
}
