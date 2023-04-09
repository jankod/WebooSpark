package hr.ja.lib;

import lombok.Data;

@Data
public class Navigation {
    private final Class<? extends Page> pageClass;
    private final String label;
    private final Icon icon;
    private String urlPath;

    public Navigation(Class<? extends Page> pageClass, String label, Icon icon) {
        this.pageClass = pageClass;
        this.label = label;
        this.icon = icon;
    }
}
