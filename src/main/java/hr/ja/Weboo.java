package hr.ja;

import hr.ja.lib.WebSite;
import hr.ja.model.User;
import lombok.extern.slf4j.Slf4j;
import spark.*;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static spark.Spark.*;

@Slf4j
public class Weboo {
    private static WebSite site = new WebSite();

    public static void main(String[] args) {
        // staticFileLocation("/public");
        staticFiles.externalLocation("/home/tag/IdeaProjects/WebooSpark/WebooSpark/src/main/resources/public");
        int port = 8080;
        Spark.port(port);

        get("/", (req, res) -> {
            HomePage page = new HomePage();
            page.get(req, res);
            String htmlBody = page.toHtml();
            return site.wrap(htmlBody);
        });
        log.debug("http://localhost:" + port);

    }

}