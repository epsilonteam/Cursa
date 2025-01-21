package net.spartanb312.cursa.config.impls.number;

import net.spartanb312.cursa.config.impls.NumberValue;

public class IntValue extends NumberValue<Integer> {

    public IntValue(String name, int value, int minValue, int maxValue) {
        super(name, value, minValue, maxValue);
    }

    public IntValue(String name, int value) {
        super(name, value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        isUnbound = true;
    }

    @Override
    public Integer getStride() {
        return getMaxValue() - getMinValue();
    }

    @Override
    public Integer clamp(Integer value) {
        int min = getMinValue();
        int max = getMaxValue();
        if (value < min) return min;
        else if (value > max) return max;
        return value;
    }

    @Override
    public void setByRate(float rate) {
        setValue((int) ((float) getStride() * rate));
    }

    @Override
    public float getAsRate() {
        return Math.clamp(getValue() / (float) getStride(), 0f, 1f);
    }

    @Override
    public boolean inRange(Integer value) {
        return value >= getMinValue() && value <= getMaxValue();
    }

}
