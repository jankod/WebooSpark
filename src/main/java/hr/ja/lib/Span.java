package hr.ja.lib;

public class Span extends Widget {
    private final String text;

    public Span(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return "<span id='%s'> %s</span>".formatted(getId(), MyUtil.escape(text));
    }
}
