package hr.ja.lib;

@FunctionalInterface
public interface BindToWeb<M> {
    String getModelValue(M t);
}