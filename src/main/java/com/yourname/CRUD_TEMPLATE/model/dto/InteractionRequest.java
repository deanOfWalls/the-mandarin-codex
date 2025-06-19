package com.yourname.CRUD_TEMPLATE.model.dto;

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
