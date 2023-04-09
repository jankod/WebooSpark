package hr.ja.lib;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Page {


    public abstract PageResponse request();

    public PageResponse show(Widget widget) {
        return PageResponse.show(widget);
    }

}
