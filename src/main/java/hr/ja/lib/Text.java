package hr.ja.lib;

public class Text extends Widget {

    private final String label;

    public Text(String label) {

        this.label = label;
    }

    @Override
    public String toHtml() {
        return label;
    }

}
