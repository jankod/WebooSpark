package hr.ja.lib;

import java.util.Map;

public class TextField extends FormField {

    private final String name;
    private final String label;

    public TextField(String name, String label) {
        this.name = name;
        this.label = label;
    }

    @Override
    public String toHtml() {

        String html = """
               <div class="mb-3">
                  <label for="{name}" class="form-label">{label}</label>
                  <input type="text" class="form-control" id="{name}" >
                </div>
                            
              """;
        return MyUtil.qute(html, Map.of("name", name, "label", label));

    }
}
