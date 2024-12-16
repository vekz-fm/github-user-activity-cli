package io.github.user_activity_cli.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubClient {
    private static final Pattern NEXT_LINK_PATTERN = Pattern.compile("<([^>]+)>; rel=\"next\"");
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();

    public List<Event> getEvents(String username) {
        List<Event> allEvents = new ArrayList<>();
        String url = "https://api.github.com/users/" + username + "/events";
        while (url != null) {
            try (HttpClient client = HttpClient.newHttpClient()) {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    throw new IOException("Error de request, codigo: " + response.statusCode());
                }
                String json = response.body();
                Type eventListType = new TypeToken<ArrayList<Event>>() {
                }.getType();
                List<Event> events = gson.fromJson(json, eventListType);
                allEvents.addAll(events);
                url = extractNextLink(response.headers().firstValue("Link").orElse(null));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return allEvents;
    }

    private String extractNextLink(String linkHeader) {
        if (linkHeader == null) return null;
        Matcher matcher = NEXT_LINK_PATTERN.matcher(linkHeader);
        if (matcher.find()) return matcher.group(1);
        return null;
    }
}

