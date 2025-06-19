package com.yourname.CRUD_TEMPLATE.service.gpt;

import com.yourname.CRUD_TEMPLATE.model.InteractionEntry;
import com.yourname.CRUD_TEMPLATE.model.dto.InteractionResponse;

/**
 * Service for interacting with GPT.
 */
public interface GptService {
    InteractionResponse analyzeInteraction(InteractionEntry entry);
}
