package hr.ja.lib;

import lombok.extern.slf4j.Slf4j;
import spark.*;

import java.util.List;

import static spark.Spark.*;

@Slf4j
public class WebooEngine {
    private final WebSite site;

    private WebooEngine(WebSite site) {
        this.site = site;
    }

    public static void start(WebSite site, int port) {
        WebooEngine webooEngine = new WebooEngine(site);
        webooEngine.start(port);
    }


    public void start(int port) {
        staticFileLocation("/public");
        //staticFiles.externalLocation("/home/tag/IdeaProjects/WebooSpark/WebooSpark/src/main/resources/public");
        port(port);

        List<PageManager> pages = site.getPages();

        for (PageManager page : pages) {
            get(page.getUrlPath(), (request, response) -> {
                String pageBodyHtml = page.getPageBodyHtml(request, response);
                return site.siteLayout(pageBodyHtml);
            });
        }

        log.debug("http://localhost:" + port);
    }
}