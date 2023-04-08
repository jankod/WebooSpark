package hr.ja.lib;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

@Slf4j
public class WebooEngine {
    private static WebSite site = new WebSite();

    @Getter
    private static List<PageManager> pages = new ArrayList<>();

    public static void add(Class<? extends Page> page) {
        PageManager pm = new PageManager(page);
        pages.add(pm);
    }

    public static void start(WebSite site, int port) {
        WebooEngine.site = site;
        staticFileLocation("/public");
        //staticFiles.externalLocation("/home/tag/IdeaProjects/WebooSpark/WebooSpark/src/main/resources/public");
        port(port);

        List<PageManager> pages = getPages();

        for (PageManager page : pages) {
            Spark.get(page.getUrlPath(), (request, response) -> handlePageRequest(page, request, response));
            Spark.post(page.getUrlPath(), (request, response) -> handlePageRequest(page, request, response));
        }

        log.debug("http://localhost:" + port);
    }

    private static Object handlePageRequest(PageManager page, Request request, Response response) {
        Context.set(request, response, site);

        PageResponse pageResponse = page.createInstance().request();
//        String pageBodyHtml = page.getPageBodyHtml(request, response);
//        return WebooEngine.site.siteLayout(pageBodyHtml);
        return pageResponse.doResponse();
    }
}