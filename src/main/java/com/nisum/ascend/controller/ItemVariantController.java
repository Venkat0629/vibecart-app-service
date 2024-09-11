package com.nisum.ascend.controller;

import com.nisum.ascend.dto.Color;
import com.nisum.ascend.dto.ItemVariantDTO;
import com.nisum.ascend.dto.Size;
import com.nisum.ascend.service.ItemVariantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "Item Variant", description = "Operations related to item variants management in the catalog")
@RestController
@RequestMapping("/vibecart/ecom")
public class ItemVariantController {

    private final ItemVariantService itemVariantService;
    private static final Logger log= LoggerFactory.getLogger(ItemVariantController.class);

    public ItemVariantController(ItemVariantService itemVariantService) {
        this.itemVariantService = itemVariantService;
    }

    @Operation(summary = "Get item variant by Item ID, color, and size", description = "Fetches item variant details based on the item ID, color, and size.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item variant found", content = @Content(schema = @Schema(implementation = ItemVariantDTO.class))),
            @ApiResponse(responseCode = "404", description = "Item variant not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/products/product/item-id/{itemID}")
    public ResponseEntity<ItemVariantDTO> getItemVariantBasedOnItemIDAndColorAndSize(
            @Parameter(description = "ID of the item") @PathVariable Long itemID,
            @Parameter(description = "Color of the item") @RequestParam Color color,
            @Parameter(description = "Size of the item") @RequestParam Size size) {
        ItemVariantDTO itemVariant = itemVariantService.itemVariantBasedOnItemIDAndColorAndSize(itemID, color, size);
        log.info("Item variant found for itemID: {}, color: {}, size: {} -> {}", itemID, color, size, itemVariant);
        return ResponseEntity.ok(itemVariant);
    }

    @Operation(summary = "Get item variant by SKU ID", description = "Fetches item variant details based on the SKU ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item variant found", content = @Content(schema = @Schema(implementation = ItemVariantDTO.class))),
            @ApiResponse(responseCode = "404", description = "Item variant not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/products/product/sku-id/{skuID}")
    public ResponseEntity<ItemVariantDTO> getProductRelatedToSkuID(
            @Parameter(description = "ID of the SKU") @PathVariable Long skuID) {
        ItemVariantDTO itemVariantBySkuID = itemVariantService.getItemVariantBySkuID(skuID);
        log.info("Item variant for SKU ID {}: {}", skuID, itemVariantBySkuID);
        return ResponseEntity.ok(itemVariantBySkuID);
    }

    @Operation(summary = "Get all item variants by SKU IDs", description = "Fetches all item variants for the provided set of SKU IDs.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item variants found", content = @Content(schema = @Schema(implementation = ItemVariantDTO.class))),
            @ApiResponse(responseCode = "204", description = "No item variants found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/products")
    public ResponseEntity<List<ItemVariantDTO>> getAllItemVariantsBasedOnSkuIDs(
            @Parameter(description = "Set of SKU IDs to retrieve item variants") @RequestBody Set<Long> skuIDs) {
        List<ItemVariantDTO> itemVariantsBySkuIDs = itemVariantService.getItemVariantsBySkuIDs(skuIDs);
        log.info("Item variants found for SKU IDs {}: {}", skuIDs, itemVariantsBySkuIDs);

        if (itemVariantsBySkuIDs.isEmpty()) {
            log.info("No item variants found for SKU IDs: {}", skuIDs);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemVariantsBySkuIDs);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ItemVariantDTO>> getAllItemVariants(@RequestParam(name = "limit", required = false) Integer limit){
        List<ItemVariantDTO> allItemVariants = itemVariantService.findAllItemVariants(limit);
        if(allItemVariants.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allItemVariants);
    }

}


