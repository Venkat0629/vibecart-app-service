package com.nisum.vibe.cart.app.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class for handling image retrieval from GitHub.
 */
@Service
public class ImageService {

    // URI for accessing GitHub content
    @Value("${github.content.uri}")
    private String githubUri;

    // GitHub username for accessing the repository
    @Value("${github.user.name}")
    private String githubUsername;

    // Name of the GitHub repository
    @Value("${github.repository.name}")
    private String repositoryName;

    // Name of the branch in the GitHub repository
    @Value("${github.branch.name}")
    private String branchName;

    /**
     * Constructs the full URL to access an image in the GitHub repository.
     *
     * @param filename the name of the image file to retrieve
     * @return the full URL as a String for accessing the specified image
     */
    public String getImageUrl(String filename) {
        return String.format("%s/%s/%s/%s/images/%s", githubUri, githubUsername, repositoryName, branchName, filename);
    }
}
