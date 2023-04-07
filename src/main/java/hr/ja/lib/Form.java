package hr.ja.lib;

import java.util.function.Consumer;

public class Form<M> extends Widget {
    public void add(FormField field) {
    }

    public void onSubmit(Consumer<M> handler) {
    }

    @Override
    public String toHtml() {

        return """
                <form>
                        ovo je forma
                </form>
                """;
    }
}
