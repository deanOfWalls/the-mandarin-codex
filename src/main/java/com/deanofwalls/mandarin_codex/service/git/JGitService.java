package com.deanofwalls.mandarin_codex.service.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * JGit-based implementation of {@link GitService}.
 */
@Service
public class JGitService implements GitService {

    @Value("${git.repo.path:}")
    private String repoPath;

    @Value("${git.branch:main}")
    private String branch;

    @Override
    public void commitChanges(List<String> paths, String message) {
        if (repoPath == null || repoPath.isEmpty()) {
            return;
        }
        try (Git git = Git.open(new File(repoPath))) {
            for (String p : paths) {
                git.add().addFilepattern(p).call();
            }
            git.commit().setMessage(message).call();
            git.push().call();
        } catch (IOException | GitAPIException e) {
            throw new RuntimeException("Git commit failed", e);
        }
    }

    @Override
    public void createPullRequest(String title, String body) {
        // Placeholder: Implement using GitHub API
    }
}
