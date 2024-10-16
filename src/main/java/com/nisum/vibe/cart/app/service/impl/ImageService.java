package com.nisum.vibe.cart.app.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Value("${github.content.uri}")
    private String githubUri;

    @Value("${github.user.name}")
    private String githubUsername;

    @Value("${github.repository.name}")
    private String repositoryName;

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

    public String generateContentType(String fileName) {
        String contentType = "application/octet-stream";
        if (fileName.endsWith(".png")) {
            contentType = "image/png";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else if (fileName.endsWith(".gif")) {
            contentType = "image/gif";
        }
        return contentType;
    }
}
