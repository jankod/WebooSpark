package hr.ja.lib;

import lombok.Data;

@Data
public class ValidatorVrapper<M> {
    private final Validator<M> validator;
    private final String name;
    private final String errorMessage;

    public ValidatorVrapper(Validator<M> validator, String name, String errorMessage) {

        this.validator = validator;
        this.name = name;
        this.errorMessage = errorMessage;
    }
}
