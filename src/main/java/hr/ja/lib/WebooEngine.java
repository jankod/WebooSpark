package hr.ja.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
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


    private static WebSite site;

    @Getter
    private static final ObjectMapper mapper = new ObjectMapper();


    @Getter
    private static List<PageManager> pages = new ArrayList<>();

    @Getter
    private static Validator validator;

    private static PageManager add(Class<? extends Page> page) {
        PageManager pm = new PageManager(page);
        pages.add(pm);
        return pm;
    }

    public static void start(WebSite site, int port) {

        for (Navigation navigation : site.getNavigations()) {
            PageManager manager = add(navigation.getPageClass());
            navigation.setUrlPath(manager.getUrlPath());
        }

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

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();


    }


    private static Object handlePageRequest(PageManager page, Request request, Response response) {
        WebSiteParam param = new WebSiteParam();
        param.setCurrentPage(page.getPage());
        Context.set(request, response, param);
        PageResponse pageResponse = page.createInstance().request();
        String resHtml = pageResponse.doResponse();
        if (resHtml != null) {
            return site.siteLayout(resHtml, param);
        }
        return null;
    }

    public static String getUrlPath(Class<? extends Page> page) {
        for (PageManager pageManager : pages) {
            Class<? extends Page> page1 = pageManager.getPage();
            if (page1.equals(page)) {
                return pageManager.getUrlPath();
            }
        }
        log.warn("Cannot find page url for page {}", page);
        return "???";
    }
}