package hr.ja.lib;

public class Row extends Widget {

    public Row(Widget... widgets) {
        addAll(widgets);
    }

    public Row col() {
        add(new Col());
        return this;
    }

    @Override
    public String toHtml() {
        return """
              <div class='row'>
                      %s
              </div>
              """.formatted(getChildrenHtml());
    }


}
