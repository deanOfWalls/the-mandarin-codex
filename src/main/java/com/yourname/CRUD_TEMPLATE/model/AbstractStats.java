package com.yourname.CRUD_TEMPLATE.model;

import java.util.Map;

/**
 * Base stats for tracking progress.
 */
public abstract class AbstractStats {
    private Map<String, Integer> mastered;
    private Map<String, Integer> unstable;

    public Map<String, Integer> getMastered() {
        return mastered;
    }

    public void setMastered(Map<String, Integer> mastered) {
        this.mastered = mastered;
    }

    public Map<String, Integer> getUnstable() {
        return unstable;
    }

    public void setUnstable(Map<String, Integer> unstable) {
        this.unstable = unstable;
    }
}
