package com.yourname.CRUD_TEMPLATE.service.interaction;

import com.yourname.CRUD_TEMPLATE.model.InteractionEntry;
import com.yourname.CRUD_TEMPLATE.model.dto.InteractionRequest;
import com.yourname.CRUD_TEMPLATE.model.dto.InteractionResponse;
import com.yourname.CRUD_TEMPLATE.service.git.GitService;
import com.yourname.CRUD_TEMPLATE.service.gpt.GptService;
import com.yourname.CRUD_TEMPLATE.service.io.YamlFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Default implementation of {@link InteractionService}.
 */
@Service
public class InteractionServiceImpl implements InteractionService {

    private final YamlFileService yamlService;
    private final GptService gptService;
    private final GitService gitService;
    @Value("${interaction.base-dir:interactions}")
    private String baseDir;

    private int index = 1;

    public InteractionServiceImpl(YamlFileService yamlService, GptService gptService, GitService gitService) {
        this.yamlService = yamlService;
        this.gptService = gptService;
        this.gitService = gitService;
    }

    @Override
    public InteractionResponse handleInteraction(InteractionRequest request) {
        LocalDateTime now = LocalDateTime.now();
        InteractionEntry entry = new InteractionEntry(index++, request.getEnglish(), request.getPinyin(), now);
        Path dir = Path.of(baseDir, getCurrentDate().format(DateTimeFormatter.ISO_DATE));
        Path inputPath = yamlService.saveInteraction(dir, entry);

        InteractionResponse response = gptService.analyzeInteraction(entry);
        Path responsePath = dir.resolve(String.format("%03d_response.yaml", entry.getIndex()));
        yamlService.writeYaml(responsePath, response);

        gitService.commitChanges(List.of(inputPath.toString(), responsePath.toString()),
                "Add interaction " + entry.getIndex());
        return response;
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
