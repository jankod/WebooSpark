import hr.ja.lib.Widget;
import hr.ja.util.FreemarkerUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFreeMarkerObjects {



    public static void main(String[] args) {

      //  String html = FreemarkerUtil.parse(new Page1());
        //log.debug(html);
        log.debug("da");


    }
}

class Compo1 extends Widget{

    @Override
    public String toHtml() {
        return "<b>Compo 1</b>";
    }
}

@Data
class Page1  extends Widget{

    private Compo1 compo1 = new Compo1();

    @Override
    public String toHtml() {
        return """
                <html>
                <body>
                <h2>Page1</h2>
                    ${compo1!"compo"}
                </body>
                </html>
                """;
    }
}
