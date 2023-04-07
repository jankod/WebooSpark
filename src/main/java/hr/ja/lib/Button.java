package hr.ja.lib;

import lombok.Getter;

public class Button extends FormField {

    @Getter
    private final String label;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String toHtml() {

        return """
              <button type='button' class='btn btn-primary'>%s</button>
              """.formatted(label);
    }
}

