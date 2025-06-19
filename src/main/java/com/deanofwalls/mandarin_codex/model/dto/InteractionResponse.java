package com.deanofwalls.mandarin_codex.model.dto;

/**
 * Response payload returned by GPT.
 */
public class InteractionResponse {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
