package net.spartanb312.cursa.config.impls.number;

import net.spartanb312.cursa.config.impls.NumberValue;

public class LongValue extends NumberValue<Long> {

    public LongValue(String name, long value, long minValue, long maxValue) {
        super(name, value, minValue, maxValue);
    }

    public LongValue(String name, long value) {
        super(name, value, Long.MIN_VALUE, Long.MAX_VALUE);
        isUnbound = true;
    }

    @Override
    public Long getStride() {
        return getMaxValue() - getMinValue();
    }

    @Override
    public Long clamp(Long value) {
        long min = getMinValue();
        long max = getMaxValue();
        if (value < min) return min;
        else if (value > max) return max;
        return value;
    }

    @Override
    public void setByRate(float rate) {
        setValue((long) ((double) getStride() * rate));
    }

    @Override
    public float getAsRate() {
        return (float) Math.clamp(getValue() / (double) getStride(), 0.0, 1.0);
    }

    @Override
    public boolean inRange(Long value) {
        return value >= getMinValue() && value <= getMaxValue();
    }

}
