package hr.ja.lib;

public abstract class PageResponse {

//    public static PageResponse redirect(Class<? extends Page> page) {
//        return null;
//    }

    public static PageResponse redirect(Page page) {
        return null;
    }

    public static PageResponse show(Widget... widgets) {

        return null;
    }


    public abstract String doResponse();

}
class ShowPageResponse extends PageResponse {

    private final Widget[] widgets;

    ShowPageResponse(Widget... widgets) {
        this.widgets = widgets;
    }

    @Override
    public String doResponse() {
//        return MyUtil.toHtml(widgets);
        return null;
    }
}
