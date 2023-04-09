package hr.ja;

import hr.ja.lib.Icon;
import hr.ja.lib.WebSite;
import hr.ja.lib.WebooEngine;

public class Start {

    public static void main(String[] args) {


        MyWebsite site = new MyWebsite();
        site.addPage(HomePage.class, "Home", Icon.house);
        site.addPage(UserPage.class, "User", Icon.user);

        WebooEngine.start(site,8080);

    }
}
