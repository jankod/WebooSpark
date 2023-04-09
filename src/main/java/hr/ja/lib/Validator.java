
package hr.ja.lib;

public interface Validator<M> {
        boolean hasError(M model);
    }