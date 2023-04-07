package hr.ja.lib;

import hr.ja.util.FreemarkerUtil;

public class Button extends FormField {
    public Button(String label) {
        add(new Text(label));
    }

    @Override
    public String toHtml() {

        return """
              <button type='button' class='btn btn-primary'><#list children as c>${c}</#list></button>
              """;
    }
}

