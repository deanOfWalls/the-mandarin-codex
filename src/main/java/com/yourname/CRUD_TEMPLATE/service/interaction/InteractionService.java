package com.yourname.CRUD_TEMPLATE.service.interaction;

import com.yourname.CRUD_TEMPLATE.model.InteractionEntry;
import com.yourname.CRUD_TEMPLATE.model.dto.InteractionRequest;
import com.yourname.CRUD_TEMPLATE.model.dto.InteractionResponse;

import java.time.LocalDate;

/**
 * Service for handling interactions.
 */
public interface InteractionService {
    InteractionResponse handleInteraction(InteractionRequest request);

    LocalDate getCurrentDate();
}
