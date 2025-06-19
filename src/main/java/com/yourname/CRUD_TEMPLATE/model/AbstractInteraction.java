package com.yourname.CRUD_TEMPLATE.model;

import java.time.LocalDateTime;

/**
 * Abstract base for user interactions.
 */
public abstract class AbstractInteraction {
    private final String english;
    private final String pinyin;
    private final LocalDateTime timestamp;

    protected AbstractInteraction(String english, String pinyin, LocalDateTime timestamp) {
        this.english = english;
        this.pinyin = pinyin;
        this.timestamp = timestamp;
    }

    public String getEnglish() {
        return english;
    }

    public String getPinyin() {
        return pinyin;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
