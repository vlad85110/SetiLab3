package controller.commands.requestcommands;

import controller.commands.config.CommandConfig;
import controller.commands.config.GetPlaceDescriptionConfig;
import exceptions.command.CommandException;
import exceptions.view.ViewException;
import model.Data;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import parser.ConfigParser;
import view.View;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class GetPlaceDescription extends AbstractRequestCommand {
    private final String xid;

    public GetPlaceDescription(ConfigParser configParser, HttpClient client, String xid) {
        super(configParser, client);
        this.xid = xid;
    }

    @Override
    protected Class<? extends CommandConfig> getConfigClass() {
        return GetPlaceDescriptionConfig.class;
    }

    @Override
    protected void handleResponse(HttpResponse<String> response, Data data, View view, CommandConfig config)
            throws CommandException {
        GetPlaceDescriptionConfig getPlaceDescriptionConfig = (GetPlaceDescriptionConfig)config;
        JSONObject object = new JSONObject(response.body());
        try {
            var placeInfo = object.getJSONObject(getPlaceDescriptionConfig.getInfoKeyName());
            var placeDesc = (String) placeInfo.get(getPlaceDescriptionConfig.getDescriptionKeyName());
            data.addDescription(xid, placeDesc);
            view.setActiveDescription(xid);
        } catch (JSONException ignored) {}
    }

    @Override
    protected URI createURI(CommandConfig config) throws CommandException {
        var builder = new URIBuilder(super.createURI(config));
        builder.setPath(builder.getPath() + xid);

        try {
            return builder.build();
        } catch (URISyntaxException e) {
            throw new CommandException(e);
        }
    }
}
