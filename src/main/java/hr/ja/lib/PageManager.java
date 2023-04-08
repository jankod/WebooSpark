package hr.ja.lib;

import hr.ja.lib.annotation.UrlPath;
import lombok.SneakyThrows;
import spark.Request;
import spark.Response;

import java.lang.reflect.Method;

public class PageManager {
    private final Class<? extends Page> page;
    private Method requestMethod;

    public PageManager(Class<? extends Page> page) {
        this.page = page;
    }

    public String getUrlPath() {
        for (Method method : page.getMethods()) {
            UrlPath getanno = method.getAnnotation(UrlPath.class);
            if (getanno != null) {
                requestMethod = method;
                return getanno.value();
            }
        }
        return null;
    }

//    @SneakyThrows
//    public String getPageBodyHtml(Request req, Response res) {
//        Page p = createInstance();
//        requestMethod.invoke(p);
//        return p.toHtml();
//    }

    @SneakyThrows
    public Page createInstance() {
        return page.getConstructor().newInstance();
    }
}
