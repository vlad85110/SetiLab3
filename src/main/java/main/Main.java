package main;

import netscape.javascript.JSObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, URISyntaxException {

        HttpClient client = HttpClient.newHttpClient();

        URIBuilder builder = new URIBuilder("https://graphhopper.com/api/1/geocode");
        builder.addParameter("locale", "en");
        builder.addParameter("q", "Цветной проезд");
        builder.addParameter("key", "a432ff40-a57c-4044-a0f7-8ff63180cf1e");// todo в отдельный экспешен

        var uri = builder.build();
        HttpRequest request = HttpRequest.
                newBuilder().uri(uri).build();

        var res = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).
                thenApply(HttpResponse::body).get();
        var obj = new JSONObject(res);

        System.out.println(obj);
    }
}
