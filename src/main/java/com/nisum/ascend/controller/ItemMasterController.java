package com.nisum.ascend.controller;

import com.nisum.ascend.dto.ItemMasterDTO;
import com.nisum.ascend.service.ItemMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Item Master", description = "Operations related to item management in the catalog")
@RestController
@RequestMapping("/vibecart/ecom/items")
public class ItemMasterController {

    private final ItemMasterService itemMasterService;
    private static final Logger log= LoggerFactory.getLogger(ItemMasterController.class);

    @Autowired
    public ItemMasterController(ItemMasterService itemMasterService) {
        this.itemMasterService = itemMasterService;
    }

    @Operation(summary = "Get all items", description = "Returns all items with optional limit and sorting criteria.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved items",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemMasterDTO.class))),
            @ApiResponse(responseCode = "204", description = "No items found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ItemMasterDTO>> getAllItems(
            @Parameter(description = "Optional limit for number of items")
            @RequestParam(name = "limit", required = false) Integer limit,
            @Parameter(description = "Optional sorting criteria")
            @RequestParam(name = "sortingCriteria", required = false) String sortingCriteria) {
        List<ItemMasterDTO> items = itemMasterService.findAll(limit, sortingCriteria);
        log.info("list items from item master: {}",items);
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Get item by ID", description = "Fetches item details based on item ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item found", content = @Content(schema = @Schema(implementation = ItemMasterDTO.class))),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/item/{id}")
    public ResponseEntity<ItemMasterDTO> getItemById(
            @Parameter(description = "ID of the item to be retrieved")
            @PathVariable Long id) {
        ItemMasterDTO itemMasterDTO = itemMasterService.findById(id);
        log.info("item details: {} specific to item id: {}",itemMasterDTO,id);
        return ResponseEntity.ok(itemMasterDTO);
    }

    @Operation(summary = "Get items by catalog name", description = "Returns all items associated with the given catalog name.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Items found", content = @Content(schema = @Schema(implementation = ItemMasterDTO.class))),
            @ApiResponse(responseCode = "204", description = "No items found for the catalog", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/catalog/{catalogName}")
    public ResponseEntity<List<ItemMasterDTO>> getItemsFromItemMasterBasedOnCatalogName(
            @Parameter(description = "Name of the catalog")
            @PathVariable String catalogName,
            @Parameter(description = "Optional sorting criteria")
            @RequestParam(required = false) String sortingCriteria) {
        List<ItemMasterDTO> itemsByCatalogName = itemMasterService.findItemsByCatalogName(catalogName, sortingCriteria);
        log.info("list of items specific to {} catalog: {}",catalogName,itemsByCatalogName);
        log.info("catalog items sorting criteria: {}",sortingCriteria);
        if (itemsByCatalogName.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemsByCatalogName);
    }

    @Operation(summary = "Get items by category name", description = "Returns all items associated with the given category name.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Items found", content = @Content(schema = @Schema(implementation = ItemMasterDTO.class))),
            @ApiResponse(responseCode = "204", description = "No items found for the category", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ItemMasterDTO>> getAllItemsBasedOnCategoryName(
            @Parameter(description = "Name of the category")
            @PathVariable String categoryName,
            @Parameter(description = "Optional sorting criteria")
            @RequestParam(name = "sortingCriteria", required = false) String sortingCriteria) {
        List<ItemMasterDTO> itemsFromItemMasterBasedOnCategoryName = itemMasterService.findByCategoryName(categoryName, sortingCriteria);
        log.info("list of items specific to {} category: {}",categoryName,itemsFromItemMasterBasedOnCategoryName);
        log.info("category items sorting criteria: {}",sortingCriteria);
        if (itemsFromItemMasterBasedOnCategoryName.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemsFromItemMasterBasedOnCategoryName);
    }

    @Operation(summary = "Get all SKU IDs by item ID", description = "Fetches all SKU IDs for a given item ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SKU IDs found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/item/{itemID}/skuIDs")
    public ResponseEntity<Map<String, List<Long>>> getAllSkuIDsByItemID(
            @Parameter(description = "ID of the item")
            @PathVariable Long itemID) {
        Map<String, List<Long>> allSkuIDsByItemID = itemMasterService.getAllSkuIDsByItemID(itemID);
        log.info("list of skuIDs: itemid: {}, skuIDs: {}", itemID, allSkuIDsByItemID.get("skuIDs"));
        return ResponseEntity.ok(allSkuIDsByItemID);
    }

    //images
    @Value("${image.upload.dir}")
    private String uploadDir;

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

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);
        } else {
            throw new RuntimeException("File not found or not readable: " + filename);
        }
    }


}