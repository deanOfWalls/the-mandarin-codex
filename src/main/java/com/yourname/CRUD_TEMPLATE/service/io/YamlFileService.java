package com.yourname.CRUD_TEMPLATE.service.io;

import com.yourname.CRUD_TEMPLATE.model.InteractionEntry;

import java.nio.file.Path;

/**
 * Service for YAML file operations.
 */
public interface YamlFileService {
    Path saveInteraction(Path baseDir, InteractionEntry entry);

    <T> T readYaml(Path path, Class<T> type);

    void writeYaml(Path path, Object data);
}
