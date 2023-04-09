package hr.ja.lib;

import org.apache.commons.lang3.StringUtils;

public class P extends Widget {
    private final String text;

    public P(String text) {
        this.text = StringUtils.trimToEmpty(text);
    }

    @Override
    public String toHtml() {
        return "<p>%s</h3>".formatted(MyUtil.escape(text));
    }
}
