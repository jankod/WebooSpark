package hr.ja.lib;

public class Layout {

    public static Row row(Col... cols) {
        return new Row(cols);
    }

    public static Col col(Widget... widgets) {
        return new Col(widgets);
    }

    public static Col col4(Widget... widgets) {
        return col(widgets).setClasses("col-4");
    }

    public static Col col2(Widget... widgets) {
        return col(widgets).setClasses("col-2");
    }



}
