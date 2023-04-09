package hr.ja.lib;

import hr.ja.UserPage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import spark.Request;
import spark.Response;

public class Context {
    private static final ThreadLocal<ReqSeq> threadLocal = new ThreadLocal<>();

    static void set(Request request, Response response, WebSiteParam site) {
        threadLocal.set(new ReqSeq(request, response, site));
    }

    public static Request request() {
        return threadLocal.get().request;
    }

    public static Response response() {
        return threadLocal.get().response;
    }

    public static WebSiteParam site() {
        return threadLocal.get().site;
    }
}

@Data
@RequiredArgsConstructor
class ReqSeq {
    final Request request;
    final Response response;
    final WebSiteParam site;
}
