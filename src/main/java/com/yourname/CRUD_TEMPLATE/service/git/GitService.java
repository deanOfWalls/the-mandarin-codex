package com.yourname.CRUD_TEMPLATE.service.git;

import java.util.List;

/**
 * Service for GitHub interactions.
 */
public interface GitService {
    void commitChanges(List<String> paths, String message);

    void createPullRequest(String title, String body);
}
