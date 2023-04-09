package hr.ja;

import hr.ja.lib.Context;
import hr.ja.lib.H3;
import hr.ja.lib.Page;
import hr.ja.lib.PageResponse;
import hr.ja.lib.annotation.UrlPath;

public class UserPage extends Page {


    @UrlPath("/user")
    public PageResponse request() {


        return show(new H3("User page"));
    }
}
