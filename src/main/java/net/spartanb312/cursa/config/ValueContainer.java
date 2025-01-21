package net.spartanb312.cursa.config;

import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import net.spartanb312.cursa.config.listener.ValueListener;
import net.spartanb312.cursa.config.visibility.VisibilityFlag;
import net.spartanb312.cursa.utils.StringAlias;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * author B312
 * last update on 2025/1/21
 */
public class ValueContainer<T> implements DelegatedValue<T>, ReadWriteProperty<Object, T> {

    private final List<ValueListener<T>> valueListeners = new ArrayList<>();
    private final VisibilityFlag<T> visibilityFlag;
    private final StringAlias nameAlias;

    private final T defaultValue;
    private T value;

    public ValueContainer(String name, T value) {
        this.value = value;
        this.defaultValue = value;
        visibilityFlag = new VisibilityFlag<>(this);
        nameAlias = new StringAlias(name, StringAlias.ENGLISH);
    }

    @Override
    public void setValue(T value) {
        if (this.value != value) {
            for (ValueListener<T> valueListener : valueListeners) {
                if (valueListener.invoke(value, value)) break;
            }
            this.value = value;
        }
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public T getValue(Object o, @NotNull KProperty<?> kProperty) {
        return getValue();
    }

    @Override
    public void setValue(Object o, @NotNull KProperty<?> kProperty, T t) {
        setValue(t);
    }

    public void silentSetValue(T value) {
        this.value = value;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public StringAlias getNameAlias() {
        return nameAlias;
    }

    public String getDefaultName() {
        return nameAlias.getName();
    }

    public String getName() {
        return nameAlias.getCurrent();
    }

    public void addValueListener(ValueListener<T> valueListener) {
        valueListeners.add(valueListener);
    }

    public boolean isVisible() {
        return visibilityFlag.test();
    }

}
