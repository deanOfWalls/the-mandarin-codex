package com.deanofwalls.mandarin_codex.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stats for trend analysis (e.g., accuracy per concept).
 */
public class StatsData extends AbstractStats {

    private Map<String, Double> accuracy = new HashMap<>();

    public Map<String, Double> getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Map<String, Double> accuracy) {
        this.accuracy = accuracy;
    }
}
