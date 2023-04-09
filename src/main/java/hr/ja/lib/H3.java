package hr.ja.lib;

import org.apache.commons.lang3.StringUtils;

public class H3 extends Widget {
    private final String text;

    public H3(String text) {
        this.text = StringUtils.trimToEmpty(text);
    }

    @Override
    public String toHtml() {
        return "<h3>%s</h3>".formatted(MyUtil.escape(text));
    }
}
