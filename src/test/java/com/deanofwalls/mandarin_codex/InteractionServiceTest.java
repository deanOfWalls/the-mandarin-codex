package com.deanofwalls.mandarin_codex;

import com.deanofwalls.mandarin_codex.model.dto.InteractionRequest;
import com.deanofwalls.mandarin_codex.service.interaction.InteractionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InteractionServiceTest {

    @Autowired
    private InteractionService interactionService;

    @Test
    void contextLoads() {
        assertThat(interactionService).isNotNull();
    }

    @Test
    void handleInteractionReturnsResponse() {
        InteractionRequest req = new InteractionRequest();
        req.setEnglish("hello");
        req.setPinyin("ni hao");
        assertThat(interactionService.handleInteraction(req)).isNotNull();
    }
}
