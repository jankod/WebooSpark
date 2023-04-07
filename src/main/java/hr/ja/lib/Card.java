package hr.ja.lib;

import jakarta.validation.constraints.NotNull;

public class Card extends Widget {

    private final Widget widgetTitle;
    private final Widget widgetBody;

    public Card(String titleText, Widget widgetBody) {
        this.widgetTitle = new Span(titleText);
        this.widgetBody = widgetBody;
    }

    public Card(@NotNull Widget widgetTitle, @NotNull Widget widgetBody) {
        this.widgetTitle = widgetTitle;
        this.widgetBody = widgetBody;
    }

    @Override
    public String toHtml() {
        return """
              <div class='card'>
                  <div class='card-title'>
                      %s
                  </div>
                  <div class="card-body">
                      %s
                  </div>
              </div>
                            """.formatted(widgetTitle.toHtml(), widgetBody.toHtml());
    }
}
