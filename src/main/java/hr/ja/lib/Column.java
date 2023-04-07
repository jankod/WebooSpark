package hr.ja.lib;

import java.util.function.Function;

public class Column<M> {
    private final String name;
    private final Function<M, Object> columnValue;

    public Column(String name, Function<M, Object> columnValue) {
        this.name = name;
        this.columnValue = columnValue;
    }
}
