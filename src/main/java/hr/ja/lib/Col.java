package hr.ja.lib;

import lombok.Getter;

public class Col extends Widget {


    @Getter
    private String classes = "col";

    public Col(Widget... widgets) {
        addAll(widgets);
    }

    public Col setClasses(String classes) {
        this.classes = classes;
        return this;
    }

    @Override
    public String toHtml() {

        return """
              <div class='%s' id='%s'>
                    %s
              </div>
              """.formatted(classes, getId(), getChildrenHtml());
    }
}
