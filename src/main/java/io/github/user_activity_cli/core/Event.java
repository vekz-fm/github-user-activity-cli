package io.github.user_activity_cli.core;

import com.google.gson.annotations.SerializedName;

public class Event {
    private String type;
    @SerializedName("created_at")
    private String createdAt;
    private Repo repo;

    public String getType() {
        return type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Repo getRepo() {
        return repo;
    }
}
