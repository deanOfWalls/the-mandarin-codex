package com.deanofwalls.mandarin_codex.model.dto;

/**
 * Request payload for user interaction.
 */
public class InteractionRequest {
    private String english;
    private String pinyin;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
