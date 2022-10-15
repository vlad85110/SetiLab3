package controller.commands;

import controller.commands.arguments.CommandArgs;
import controller.commands.config.CommandConfig;
import controller.commands.requestcommands.GetWeather;
import controller.commands.requestcommands.SearchPlaces;
import exceptions.command.CommandException;
import exceptions.view.ViewException;
import model.Condition;
import model.Data;
import view.View;

import java.util.concurrent.CompletableFuture;

public class ChooseLocation extends AbstractCommand {
    private final CommandArgs args;

    public ChooseLocation(CommandArgs args) {
        super(args.parser());
        this.args = args;
    }

    @Override
    public Condition run(Data data, View view) throws CommandException {
        int index;
        try {
            index = Integer.parseInt(args.params());
        } catch (NumberFormatException e) {
            throw new CommandException("wrong args");
        }

        var location = data.getLocation(index);
        data.setChosenLocation(location);
        view.clearPlaces();
        view.clearWeather();

        var point = location.getPoint();

        GetWeather getWeather = new GetWeather(configParser, args.client(), point);
        SearchPlaces searchPlaces = new SearchPlaces(configParser, args.client(), point);

        if (location.getWeather() == null) {
            Runnable runWeather = () -> {
                try {
                    getWeather.run(data, view);
                } catch (CommandException ignored) {}
            };
            CompletableFuture.runAsync(runWeather);
        } else {
            try {
                view.updateWeather(location.getWeather());
            } catch (ViewException e) {
                throw new RuntimeException(e);
            }
        }

        if (!data.isLocationContainsPlaces(location)) {
            Runnable runSearchPlaces = () -> {
                try {
                    searchPlaces.run(data, view);
                } catch (CommandException ignored) {}
            };
            CompletableFuture.runAsync(runSearchPlaces);
        } else {
            try {
                view.updatePlaces(data.getPlaces(location));
            } catch (ViewException e) {
                throw new CommandException(e);
            }
        }

        return Condition.CONTINUE;
    }

    @Override
    protected Class<? extends CommandConfig> getConfigClass() {
        return null;
    }
}
