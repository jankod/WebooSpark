package hr.ja;

import hr.ja.lib.H3;
import hr.ja.lib.Page;
import hr.ja.lib.PageResponse;
import hr.ja.lib.annotation.UrlPath;

public class UserPage extends Page {

    private final String message;

    public UserPage(String message) {
        this.message = message;
    }

    @UrlPath("/user")
    public PageResponse request() {
        return PageResponse.show(new H3(message));
    }
}
