package com.deanofwalls.mandarin_codex.service.interaction;

import com.deanofwalls.mandarin_codex.model.InteractionEntry;
import com.deanofwalls.mandarin_codex.model.ProgressStats;
import com.deanofwalls.mandarin_codex.model.StatsData;
import com.deanofwalls.mandarin_codex.model.dto.InteractionRequest;
import com.deanofwalls.mandarin_codex.model.dto.InteractionResponse;
import com.deanofwalls.mandarin_codex.service.git.GitService;
import com.deanofwalls.mandarin_codex.service.gpt.GptService;
import com.deanofwalls.mandarin_codex.service.io.YamlFileService;
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

        Path progressPath = dir.resolve("progress.yaml");
        ProgressStats progress = yamlService.readYaml(progressPath, ProgressStats.class, new ProgressStats());
        progress.getMastered().merge(request.getEnglish(), 1, Integer::sum);
        yamlService.writeYaml(progressPath, progress);

        Path statsPath = dir.resolve("stats.yaml");
        StatsData stats = yamlService.readYaml(statsPath, StatsData.class, new StatsData());
        stats.getAccuracy().merge("overall", 1.0, Double::sum);
        yamlService.writeYaml(statsPath, stats);

        Path historyPath = dir.resolve("history.log");
        String summary = String.format("%03d | %s / %s -> %s%n", entry.getIndex(), request.getEnglish(), request.getPinyin(), response.getContent());
        yamlService.writeString(historyPath, summary, true);

        gitService.commitChanges(List.of(
                        inputPath.toString(),
                        responsePath.toString(),
                        progressPath.toString(),
                        statsPath.toString(),
                        historyPath.toString()),
                "Add interaction " + entry.getIndex());
        return response;
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
