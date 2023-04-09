package hr.ja.lib;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public class H3 extends Widget {
    private final String text;

    @Getter
    @Setter
    private String classes = "";

    public H3(String text) {
        this.text = StringUtils.trimToEmpty(text);
    }

    @Override
    public String toHtml() {
        return "<h3 class='%s'>%s</h3>".formatted(classes, MyUtil.escape(text));
    }
}
