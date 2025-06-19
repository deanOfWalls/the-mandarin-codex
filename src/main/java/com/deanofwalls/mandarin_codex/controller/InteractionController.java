package com.deanofwalls.mandarin_codex.controller;

import com.deanofwalls.mandarin_codex.model.dto.InteractionRequest;
import com.deanofwalls.mandarin_codex.model.dto.InteractionResponse;
import com.deanofwalls.mandarin_codex.service.interaction.InteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for interaction endpoint.
 */
@RestController
@RequestMapping("/interaction")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping
    public ResponseEntity<InteractionResponse> handle(@RequestBody InteractionRequest request) {
        InteractionResponse response = interactionService.handleInteraction(request);
        return ResponseEntity.ok(response);
    }
}
