package net.spartanb312.cursa.config;

public interface DelegatedValue<T> {
    void setValue(T value);
    T getValue();
}
