package hr.ja.lib;

public class Col extends Widget {

    public Col(Widget... widgets) {
        addAll(widgets);
    }

    @Override
    public String toHtml() {

        return """
              <div class='col' id='%s'>
                    %s
              </div>
              """.formatted(getId(), getChildrenHtml());
    }
}
