package hr.ja.lib;

import hr.ja.lib.annotation.Get;
import lombok.SneakyThrows;
import spark.Request;
import spark.Response;

import java.lang.reflect.Method;

public class PageManager {
    private final Class<? extends Page> page;
    private Method getMethod;

    public PageManager(Class<? extends Page> page) {
        this.page = page;
    }

    public String getUrlPath() {
        for (Method method : page.getMethods()) {
            Get getanno = method.getAnnotation(Get.class);
            if (getanno != null) {
                getMethod = method;
                return getanno.value();
            }
        }
        return null;
    }

    @SneakyThrows
    public String getPageBodyHtml(Request req, Response res) {
        Page p = createInstance();
        getMethod.invoke(p, req, res);
        return p.toHtml();
    }

    @SneakyThrows
    private Page createInstance() {
        return page.getConstructor().newInstance();
    }
}
