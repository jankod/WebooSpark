package hr.ja.lib;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Page {


    public abstract PageResponse request();
//    @Getter
//    private final List<Widget> widgetList = new ArrayList<>();
//
//    public void add(Widget... widgets) {
//        Collections.addAll(widgetList, widgets);
//    }
//
//
//    public String toHtml() {
//      return MyUtil.toHtml(widgetList);
//    }

}
