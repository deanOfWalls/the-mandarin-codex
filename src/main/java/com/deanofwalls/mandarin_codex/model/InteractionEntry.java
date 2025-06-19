package com.deanofwalls.mandarin_codex.model;

import java.time.LocalDateTime;

/**
 * Concrete interaction entry with index.
 */
public class InteractionEntry extends AbstractInteraction {
    private final int index;

    public InteractionEntry(int index, String english, String pinyin, LocalDateTime timestamp) {
        super(english, pinyin, timestamp);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
