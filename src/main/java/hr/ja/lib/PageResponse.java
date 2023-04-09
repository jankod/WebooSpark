package hr.ja.lib;

import hr.ja.UserPage;
import spark.Session;

import java.util.List;

public abstract class PageResponse {


    public static PageResponse show(Widget... widgets) {
        return new ShowPageResponse(widgets);
    }

    public static PageResponse redirect(Class<? extends Page> page, String message) {
        String urlPath = WebooEngine.getUrlPath(page);
        return new RedirectPageResponse(urlPath, message);
    }


    public abstract String doResponse();

}

class RedirectPageResponse extends PageResponse {


    private final String path;
    private final String flashMessage;

    public RedirectPageResponse(String path, String flashMessage) {
        this.path = path;
        this.flashMessage = flashMessage;
    }

    @Override
    public String doResponse() {
        Session session = Context.request().session(true);
        session.attribute("FLASH", flashMessage);
        Context.response().redirect(path);
        return null;
    }
}

class ShowPageResponse extends PageResponse {

    private final Widget[] widgets;

    ShowPageResponse(Widget... widgets) {
        this.widgets = widgets;
    }

    @Override
    public String doResponse() {
        //return Context.site().siteLayout(htmlPageBody);
        return MyUtil.toHtml(List.of(widgets));
    }
}
