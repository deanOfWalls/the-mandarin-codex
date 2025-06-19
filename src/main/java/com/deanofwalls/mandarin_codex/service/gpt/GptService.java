package com.deanofwalls.mandarin_codex.service.gpt;

import com.deanofwalls.mandarin_codex.model.InteractionEntry;
import com.deanofwalls.mandarin_codex.model.dto.InteractionResponse;

/**
 * Service for interacting with GPT.
 */
public interface GptService {
    InteractionResponse analyzeInteraction(InteractionEntry entry);
}
