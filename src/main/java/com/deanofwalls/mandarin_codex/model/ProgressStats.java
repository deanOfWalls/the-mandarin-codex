package com.deanofwalls.mandarin_codex.model;

import java.util.HashMap;

/**
 * Concrete stats for progress tracking.
 */
public class ProgressStats extends AbstractStats {

    public ProgressStats() {
        setMastered(new HashMap<>());
        setUnstable(new HashMap<>());
    }
}
