package io.github.user_activity_cli.core;

import java.util.Objects;

public class Repo {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Repo repo = (Repo) o;
        return Objects.equals(name, repo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
