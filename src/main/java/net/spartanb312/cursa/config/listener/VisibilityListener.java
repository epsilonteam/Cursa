package net.spartanb312.cursa.config.listener;

import net.spartanb312.cursa.config.DelegatedValue;

public interface VisibilityListener<T> {
    /**
     * @param value:   value container
     * @param visible: current visibility
     */
    void onVisibilityChange(DelegatedValue<T> value, boolean visible);
}
