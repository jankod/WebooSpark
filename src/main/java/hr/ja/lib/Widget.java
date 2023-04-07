package hr.ja.lib;

import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Widget implements HashId {


    @Getter
    private final String id = RandomStringUtils.randomAlphanumeric(10);

    @Getter
    private List<Widget> children = new ArrayList<>();

    public Widget add(Widget widget) {
        children.add(widget);
        return this;
    }

    public void addAll(Widget... widgets) {
        Collections.addAll(children, widgets);
    }

    public abstract String toHtml();

    public String getChildrenHtml() {
        return MyUtil.toHtml(getChildren());
    }
}
