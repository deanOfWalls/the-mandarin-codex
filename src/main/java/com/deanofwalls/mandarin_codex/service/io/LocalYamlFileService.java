package com.deanofwalls.mandarin_codex.service.io;

import com.deanofwalls.mandarin_codex.model.InteractionEntry;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
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
            try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                yaml.dump(entry, writer);
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
    public <T> T readYaml(Path path, Class<T> type, T defaultValue) {
        try (InputStream is = Files.newInputStream(path)) {
            return yaml.loadAs(is, type);
        } catch (IOException e) {
            return defaultValue;
        }
    }

    @Override
    public void writeYaml(Path path, Object data) {
        try {
            Files.createDirectories(path.getParent());
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                yaml.dump(data, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write YAML", e);
        }
    }

    @Override
    public void writeString(Path path, String data, boolean append) {
        try {
            Files.createDirectories(path.getParent());
            if (append) {
                Files.writeString(path, data, StandardCharsets.UTF_8,
                        Files.exists(path) ? java.nio.file.StandardOpenOption.APPEND : java.nio.file.StandardOpenOption.CREATE);
            } else {
                Files.writeString(path, data, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write text", e);
        }
    }
}
