package hr.ja.lib;

import spark.Request;

@FunctionalInterface
public interface BindFromWeb<M> {
    void bind(Request request, M model);
}