package hr.ja.lib;

import io.quarkus.qute.Qute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Table<M> extends Widget {

    private Collection<M> data;

    private List<Column<M>> columns = new ArrayList<>();

    public Column<M> column(String name, Function<M, Object> columnValue) {
        Column<M> column = new Column<>(name, columnValue);
        columns.add(column);
        return column;
    }

    public void setData(Collection<M> data) {
        this.data = data;
    }

    public TableConfiguration getConfiguration() {
        return null;
    }

    public TableBrowser browser() {
        return new TableBrowser();
    }

    @Override
    public String toHtml() {
        String html = """
              <div id="{id}">
                            
              </div>
              <script>
                       const tab = new Tabulator("#{id}" , {
                           height: 215
                       });
              </script>
                             
                            """;
        return MyUtil.qute(html, Map.of("id", getId()));
    }
}
