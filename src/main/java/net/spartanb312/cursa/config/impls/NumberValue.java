package net.spartanb312.cursa.config.impls;

import net.spartanb312.cursa.config.ValueContainer;

public abstract class NumberValue<T extends Number> extends ValueContainer<T> {

    private OutOfBoundStrategy strategy = OutOfBoundStrategy.CLAMP;
    protected boolean isUnbound = false;
    private T minValue;
    private T maxValue;

    public NumberValue(String name, T value, T minValue, T maxValue) {
        super(name, value);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public abstract T getStride();

    public abstract void setByRate(float rate);

    public abstract float getAsRate();

    public abstract boolean inRange(T value);

    public abstract T clamp(T value);

    public void setBoundary(T minValue, T maxValue) throws IllegalAccessException {
        if (isUnbound) throw new IllegalAccessException("Unbounded values cannot have boundaries");
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void setValue(T value) {
        if (inRange(value)) super.setValue(value);
        else if (strategy == OutOfBoundStrategy.CLAMP) super.setValue(clamp(value));
        else if (strategy == OutOfBoundStrategy.THROW) {
            throw new IllegalArgumentException("Value " + value + " out of bound (" + getMinValue() + "," + getMaxValue() + ")");
        }
    }

    public OutOfBoundStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(OutOfBoundStrategy strategy) {
        this.strategy = strategy;
    }

    public T getMinValue() {
        return minValue;
    }

    public T getMaxValue() {
        return maxValue;
    }

    public enum OutOfBoundStrategy {
        CLAMP,
        THROW,
        NOTHING
    }

}
