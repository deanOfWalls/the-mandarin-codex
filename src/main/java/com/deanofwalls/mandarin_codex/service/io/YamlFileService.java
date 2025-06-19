package com.deanofwalls.mandarin_codex.service.io;

import com.deanofwalls.mandarin_codex.model.InteractionEntry;

import java.nio.file.Path;

/**
 * Service for YAML file operations.
 */
public interface YamlFileService {
    Path saveInteraction(Path baseDir, InteractionEntry entry);

    <T> T readYaml(Path path, Class<T> type);

    void writeYaml(Path path, Object data);
}
