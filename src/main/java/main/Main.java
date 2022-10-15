package main;

import exceptions.command.CommandException;
import exceptions.controller.ControllerException;
import exceptions.view.ViewException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, URISyntaxException, CommandException {

//        HttpClient client = HttpClient.newHttpClient();
//
//        URIBuilder builder = new URIBuilder("https://graphhopper.com/api/1/geocode");
//        builder.addParameter("locale", "en");
//        builder.addParameter("q", "Цветной проезд");
//        builder.addParameter("key", "a432ff40-a57c-4044-a0f7-8ff63180cf1e");
//
//        var uri = builder.build();
//        HttpRequest request = HttpRequest.
//                newBuilder().uri(uri).build();
//
//        var res = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).
//                thenApply(HttpResponse::body).get();
//        System.out.println(obj);

//        ConfigParser configParser = new ConfigParser();
//        HttpClient client = HttpClient.newHttpClient();
//
//        Data data = new Data();
//        SearchLocations searchLocation = new SearchLocations(new ConfigParser(), "цветной проезд", client);
//        searchLocation.run(data);
//        View view = new GraphicsView();
//        try {
//            view.updateView(Condition.LocationChoosing, data.getLocations());
//        } catch (ViewException e) {
//            throw new RuntimeException(e);
//        }

        Executor executor = new Executor();
        try {
            executor.startApp();
        } catch (ViewException | ControllerException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
     }
}
