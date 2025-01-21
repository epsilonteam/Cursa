package net.spartanb312.cursa.config.listener;

public interface ValueListener<T> {
    /**
     * @param preValue: old value
     * @param newValue: new value
     * @return true: cancel this event
     */
    boolean invoke(T preValue, T newValue);
}
