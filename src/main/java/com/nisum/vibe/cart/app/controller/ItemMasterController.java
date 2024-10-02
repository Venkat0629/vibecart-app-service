package com.nisum.vibe.cart.app.controller;

import com.nisum.vibe.cart.app.dto.ItemMasterDTO;
import com.nisum.vibe.cart.app.service.ItemMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vibe-cart/app/items")
public class ItemMasterController {

    private final ItemMasterService itemMasterService;
    private static final Logger log = LoggerFactory.getLogger(ItemMasterController.class);
    @Value("${image.upload.dir}")
    private String uploadDir;

    @Autowired
    public ItemMasterController(ItemMasterService itemMasterService) {
        this.itemMasterService = itemMasterService;
    }

    @GetMapping
    public ResponseEntity<List<ItemMasterDTO>> getAllItems(@RequestParam(name = "limit", required = false) Integer limit, @RequestParam(name = "sortingCriteria", required = false) String sortingCriteria) {
        List<ItemMasterDTO> items = itemMasterService.findAll(limit, sortingCriteria);
        log.info("list items from item master: {}", items);
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemMasterDTO> getItemById(@PathVariable Long id) {
        ItemMasterDTO itemMasterDTO = itemMasterService.findById(id);
        log.info("item details: {} specific to item id: {}", itemMasterDTO, id);
        return ResponseEntity.ok(itemMasterDTO);
    }

    @GetMapping("/catalog/{catalogName}")
    public ResponseEntity<List<ItemMasterDTO>> getItemsFromItemMasterBasedOnCatalogName(@PathVariable String catalogName, @RequestParam(required = false) String sortingCriteria) {
        List<ItemMasterDTO> itemsByCatalogName = itemMasterService.findItemsByCatalogName(catalogName, sortingCriteria);
        log.info("list of items specific to {} catalog: {}", catalogName, itemsByCatalogName);
        log.info("catalog items sorting criteria: {}", sortingCriteria);
        if (itemsByCatalogName.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemsByCatalogName);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ItemMasterDTO>> getAllItemsBasedOnCategoryName(@PathVariable String categoryName, @RequestParam(name = "sortingCriteria", required = false) String sortingCriteria) {
        List<ItemMasterDTO> itemsFromItemMasterBasedOnCategoryName = itemMasterService.findByCategoryName(categoryName, sortingCriteria);
        log.info("list of items specific to {} category: {}", categoryName, itemsFromItemMasterBasedOnCategoryName);
        log.info("category items sorting criteria: {}", sortingCriteria);
        if (itemsFromItemMasterBasedOnCategoryName.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemsFromItemMasterBasedOnCategoryName);
    }

    @GetMapping("/item/{itemID}/skuIDs")
    public ResponseEntity<Map<String, List<Long>>> getAllSkuIDsByItemID(@PathVariable Long itemID) {
        Map<String, List<Long>> allSkuIDsByItemID = itemMasterService.getAllSkuIDsByItemID(itemID);
        log.info("list of skuIDs: itemid: {}, skuIDs: {}", itemID, allSkuIDsByItemID.get("skuIDs"));
        return ResponseEntity.ok(allSkuIDsByItemID);
    }


    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws Exception {
        // Construct the full path to the image file
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();

        // Create a UrlResource from the file path
        Resource resource = new UrlResource(filePath.toUri());

        // Check if the resource exists and is readable
        if (resource.exists() && resource.isReadable()) {
            // Determine the content type
            String contentType = "application/octet-stream"; // Fallback type
            if (filename.endsWith(".png")) {
                contentType = "image/png";
            } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
                contentType = "image/jpeg";
            } else if (filename.endsWith(".gif")) {
                contentType = "image/gif";
            }

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"").body(resource);
        } else {
            throw new RuntimeException("File not found or not readable: " + filename);
        }
    }


}