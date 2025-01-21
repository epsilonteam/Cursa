package net.spartanb312.cursa.config.impls;

import net.spartanb312.cursa.config.ValueContainer;
import net.spartanb312.cursa.utils.DisplayEnum;

public class EnumValue<E extends Enum<E>> extends ValueContainer<Enum<E>> {

    public EnumValue(String name, Enum<E> value) {
        super(name, value);
    }

    public String getValueDisplayName() {
        Enum<E> value = getValue();
        if (value instanceof DisplayEnum) return ((DisplayEnum) value).displayName();
        else return value.name();
    }

}
