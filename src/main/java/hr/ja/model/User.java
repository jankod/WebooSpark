package hr.ja.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@NoArgsConstructor
public class User {

    private Long id;

    @NotEmpty
    private String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
