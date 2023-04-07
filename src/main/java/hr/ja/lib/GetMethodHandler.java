package hr.ja.lib;

import spark.Request;
import spark.Response;

public interface GetMethodHandler {

    void get(Request req, Response res);
}
