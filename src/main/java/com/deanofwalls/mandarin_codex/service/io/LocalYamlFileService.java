package com.deanofwalls.mandarin_codex.service.io;

import com.deanofwalls.mandarin_codex.model.InteractionEntry;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Local file system implementation of {@link YamlFileService}.
 */
@Service
public class LocalYamlFileService implements YamlFileService {

    private final Yaml yaml = new Yaml();

    @Override
    public Path saveInteraction(Path baseDir, InteractionEntry entry) {
        try {
            Files.createDirectories(baseDir);
            String fileName = String.format("%03d_input.yaml", entry.getIndex());
            Path file = baseDir.resolve(fileName);
            try (OutputStream os = Files.newOutputStream(file)) {
                yaml.dump(entry, os);
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save interaction", e);
        }
    }

    @Override
    public <T> T readYaml(Path path, Class<T> type) {
        try (InputStream is = Files.newInputStream(path)) {
            return yaml.loadAs(is, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read YAML", e);
        }
    }

    @Override
    public void writeYaml(Path path, Object data) {
        try {
            Files.createDirectories(path.getParent());
            try (OutputStream os = Files.newOutputStream(path)) {
                yaml.dump(data, os);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write YAML", e);
        }
    }
}
