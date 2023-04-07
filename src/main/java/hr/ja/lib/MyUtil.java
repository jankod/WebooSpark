package hr.ja.lib;

import io.quarkus.qute.Escaper;
import io.quarkus.qute.Qute;

import java.util.List;
import java.util.Map;

public class MyUtil {
    private static final Escaper e = Escaper.builder().build();

    public static String escape(String text) {
        return e.escape(text);
    }

    public static String toHtml(List<Widget> widgetList) {
        StringBuilder html = new StringBuilder();
        for (Widget widget : widgetList) {
            html.append(widget.toHtml());
        }
        return html.toString();
    }

    public static String qute(String html, Map<String, Object> map) {
        return Qute.fmt(html, map);
    }
}
