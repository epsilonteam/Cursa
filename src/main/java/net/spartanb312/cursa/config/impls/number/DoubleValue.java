package net.spartanb312.cursa.config.impls.number;

import net.spartanb312.cursa.config.impls.NumberValue;

public class DoubleValue extends NumberValue<Double> {

    public DoubleValue(String name, double value, double minValue, double maxValue) {
        super(name, value, minValue, maxValue);
    }

    public DoubleValue(String name, double value) {
        super(name, value, Double.MIN_VALUE, Double.MAX_VALUE);
        isUnbound = true;
    }

    @Override
    public Double getStride() {
        return getMaxValue() - getMinValue();
    }

    @Override
    public Double clamp(Double value) {
        double min = getMinValue();
        double max = getMaxValue();
        if (value < min) return min;
        else if (value > max) return max;
        return value;
    }

    @Override
    public void setByRate(float rate) {
        setValue(getStride() * rate);
    }

    @Override
    public float getAsRate() {
        return (float) Math.clamp(getValue() / getStride(), 0.0, 1.0);
    }

    @Override
    public boolean inRange(Double value) {
        return value >= getMinValue() && value <= getMaxValue();
    }

}
