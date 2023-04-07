package hr.ja.lib;

public class Layout {

    public static Row row(Col... cols) {
        return new Row(cols);
    }

    public static Col col(Widget... widgets) {
        return new Col(widgets);
    }

}
