package hr.ja.lib;

import hr.ja.lib.annotation.UrlPath;
import lombok.Getter;
import lombok.SneakyThrows;
import spark.Request;
import spark.Response;

import java.lang.reflect.Method;

public class PageManager {
    @Getter
    private final Class<? extends Page> page;

    private Method requestMethod;

    @Getter
    private String urlPath;

    public PageManager(Class<? extends Page> page) {
        this.page = page;
        this.urlPath = findUrlPath();
    }

    private String findUrlPath() {
        for (Method method : page.getMethods()) {
            UrlPath getanno = method.getAnnotation(UrlPath.class);
            if (getanno != null) {
                requestMethod = method;
                return getanno.value();
            }
        }
        return null;
    }

    @SneakyThrows
    public Page createInstance() {
        return page.getConstructor().newInstance();
    }
}
