package com.nisum.vibe.cart.app.controller;

import com.nisum.vibe.cart.app.dto.Color;
import com.nisum.vibe.cart.app.dto.ItemVariantDTO;
import com.nisum.vibe.cart.app.dto.Size;
import com.nisum.vibe.cart.app.service.ItemVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/vibe-cart/app")
public class ItemVariantController {

    private final ItemVariantService itemVariantService;
    private static final Logger log = LoggerFactory.getLogger(ItemVariantController.class);

    public ItemVariantController(ItemVariantService itemVariantService) {
        this.itemVariantService = itemVariantService;
    }


    @GetMapping("/products/product/item-id/{itemID}")
    public ResponseEntity<ItemVariantDTO> getItemVariantBasedOnItemIDAndColorAndSize(@PathVariable Long itemID, @RequestParam Color color, @RequestParam Size size) {
        ItemVariantDTO itemVariant = itemVariantService.itemVariantBasedOnItemIDAndColorAndSize(itemID, color, size);
        log.info("Item variant found for itemID: {}, color: {}, size: {} -> {}", itemID, color, size, itemVariant);
        return ResponseEntity.ok(itemVariant);
    }


    @GetMapping("/products/product/sku-id/{skuID}")
    public ResponseEntity<ItemVariantDTO> getProductRelatedToSkuID(@PathVariable Long skuID) {
        ItemVariantDTO itemVariantBySkuID = itemVariantService.getItemVariantBySkuID(skuID);
        log.info("Item variant for SKU ID {}: {}", skuID, itemVariantBySkuID);
        return ResponseEntity.ok(itemVariantBySkuID);
    }

    @PostMapping("/products")
    public ResponseEntity<List<ItemVariantDTO>> getAllItemVariantsBasedOnSkuIDs(@RequestBody Set<Long> skuIDs) {
        List<ItemVariantDTO> itemVariantsBySkuIDs = itemVariantService.getItemVariantsBySkuIDs(skuIDs);
        log.info("Item variants found for SKU IDs {}: {}", skuIDs, itemVariantsBySkuIDs);

        if (itemVariantsBySkuIDs.isEmpty()) {
            log.info("No item variants found for SKU IDs: {}", skuIDs);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemVariantsBySkuIDs);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ItemVariantDTO>> getAllItemVariants(@RequestParam(name = "limit", required = false) Integer limit) {
        List<ItemVariantDTO> allItemVariants = itemVariantService.findAllItemVariants(limit);
        if (allItemVariants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allItemVariants);
    }

}


