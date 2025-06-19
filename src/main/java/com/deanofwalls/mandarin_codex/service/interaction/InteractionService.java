package com.deanofwalls.mandarin_codex.service.interaction;

import com.deanofwalls.mandarin_codex.model.InteractionEntry;
import com.deanofwalls.mandarin_codex.model.dto.InteractionRequest;
import com.deanofwalls.mandarin_codex.model.dto.InteractionResponse;

import java.time.LocalDate;

/**
 * Service for handling interactions.
 */
public interface InteractionService {
    InteractionResponse handleInteraction(InteractionRequest request);

    LocalDate getCurrentDate();
}
