package hr.ja.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class User {

    private Long id;

    @NotNull
    private String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
