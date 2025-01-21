package net.spartanb312.cursa.config.visibility;

import net.spartanb312.cursa.config.DelegatedValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class VisibilityFlag<T> {

    private final List<Predicate<T>> conditions = new ArrayList<>();
    private final DelegatedValue<T> value;

    public VisibilityFlag(DelegatedValue<T> value) {
        this.value = value;
    }

    public void addCondition(final Predicate<T> condition) {
        conditions.add(condition);
    }

    public boolean test() {
        for (final Predicate<T> condition : conditions) {
            if (!condition.test(value.getValue())) return false;
        }
        return true;
    }

}
