package hr.ja;

import hr.ja.lib.WebSite;
import hr.ja.lib.WebooEngine;

public class Start {

    public static void main(String[] args) {

        WebSite site = new WebSite();
        site.add(HomePage.class);

        WebooEngine.start(site, 8080);

    }
}
