package hr.ja.lib;

public class HtmlUtil {


    public static H3 h3(String text) {
        return new H3(text);
    }

    public static H3 h3(String text, String classes) {
        H3 h3 = new H3(text);
        h3.setClasses(classes);
        return h3;
    }

    public static P p(String text) {
        return new P(text);
    }

}
