package hr.ja.lib;

@FunctionalInterface
public interface SubmitHandler<T> {

    void submit(T t);
}
