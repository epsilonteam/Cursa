package net.spartanb312.cursa.config.impls.number;

import net.spartanb312.cursa.config.impls.NumberValue;

public class FloatValue extends NumberValue<Float> {

    public FloatValue(String name, float value, float minValue, float maxValue) {
        super(name, value, minValue, maxValue);
    }

    public FloatValue(String name, float value) {
        super(name, value, Float.MIN_VALUE, Float.MAX_VALUE);
        isUnbound = true;
    }

    @Override
    public Float getStride() {
        return getMaxValue() - getMinValue();
    }

    @Override
    public Float clamp(Float value) {
        float min = getMinValue();
        float max = getMaxValue();
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
        return Math.clamp(getValue() / getStride(), 0f, 1f);
    }

    @Override
    public boolean inRange(Float value) {
        return value >= getMinValue() && value <= getMaxValue();
    }

}
