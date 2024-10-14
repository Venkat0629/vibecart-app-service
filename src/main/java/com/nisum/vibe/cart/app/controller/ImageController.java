package com.nisum.vibe.cart.app.controller;

import com.nisum.vibe.cart.app.service.impl.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;

/**
 * Controller for handling image retrieval requests.
 */
@RestController
@RequestMapping("/api/v1/vibe-cart/app/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);
    private final ImageService imageService;

    /**
     * Constructs an ImageController with the specified ImageService.
     *
     * @param imageService the service used to retrieve image URLs
     */
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Retrieves an image by filename.
     *
     * @param filename the name of the image file to retrieve
     * @return a ResponseEntity containing the image as a Resource
     * @throws ResponseStatusException if the file is not found or not readable
     */
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        log.info("Attempting to retrieve file: {}", filename);
        try {
            String imageUrl = imageService.getImageUrl(filename).replace(" ", "%20");
            URL fileUrl = new URL(imageUrl);
            Resource resource = new UrlResource(fileUrl);

            // Check if the resource exists and is readable
            if (!resource.exists() || !resource.isReadable()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found or not readable: " + filename);
            }

            // Return the image resource with appropriate headers
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpeg")).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"").body(resource);

        } catch (Exception e) {
            log.error("Error retrieving file: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving file: " + filename);
        }
    }
}
