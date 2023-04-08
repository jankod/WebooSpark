package hr.ja.lib;

public class H3 extends Widget {
    private final String text;

    public H3(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return "<h3>%s</h3>".formatted(MyUtil.escape(text));
    }
}
