package com.yourname.CRUD_TEMPLATE.service.gpt;

import com.yourname.CRUD_TEMPLATE.model.InteractionEntry;
import com.yourname.CRUD_TEMPLATE.model.dto.InteractionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation that calls OpenAI's GPT-4o API.
 */
@Service
public class OpenAIGptService implements GptService {

    @Value("${openai.api.key:}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public InteractionResponse analyzeInteraction(InteractionEntry entry) {
        InteractionResponse response = new InteractionResponse();
        if (apiKey == null || apiKey.isEmpty()) {
            response.setContent("API key not configured");
            return response;
        }
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-4o");
        List<Map<String, String>> messages = List.of(
                Map.of("role", "system", "content", "You are a Mandarin tutor."),
                Map.of("role", "user", "content", entry.getEnglish() + " / " + entry.getPinyin())
        );
        request.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        try {
            Map<?, ?> result = restTemplate.postForObject(url, entity, Map.class);
            Object choices = ((List<?>) result.get("choices")).get(0);
            if (choices instanceof Map<?, ?> choice) {
                Map<?, ?> message = (Map<?, ?>) choice.get("message");
                response.setContent(message.get("content").toString());
            }
        } catch (Exception e) {
            response.setContent("Failed to call OpenAI API: " + e.getMessage());
        }
        return response;
    }
}
