package hr.ja.lib;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * https://getbootstrap.com/docs/5.2/layout/containers/#how-they-work
 */
@Getter @Setter
public class Container extends Widget {

    enum Type {
        NONE(""), SM("-sm"), MD(".md"), LG("-lg"), XL("-xl"), XXL("xxl"), FLUID("-fluid");

        @Getter
        private final String sufix;

        Type(String sufix) {

            this.sufix = sufix;
        }
    }


    private Type type = Type.NONE;

    public Container() {

    }

    @Override
    public String toHtml() {
        return """
                <div class='container${type.sufix}'>${children}</div>
                """;
    }
}
