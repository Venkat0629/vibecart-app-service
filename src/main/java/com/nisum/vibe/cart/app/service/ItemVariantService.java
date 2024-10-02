package com.nisum.vibe.cart.app.service;

import com.nisum.vibe.cart.app.dto.Color;
import com.nisum.vibe.cart.app.dto.Size;
import com.nisum.vibe.cart.app.dto.ItemVariantDTO;

import java.util.List;
import java.util.Set;

public interface ItemVariantService {

    List<ItemVariantDTO> getAllVariantsByItemMasterItemID(Long itemMasterItemID);

    ItemVariantDTO getItemVariantBySkuID(Long skuID);

    ItemVariantDTO itemVariantBasedOnItemIDAndColorAndSize(Long itemID, Color color, Size size);

    List<ItemVariantDTO> getItemVariantsBySkuIDs(Set<Long> skuIDs);

    List<ItemVariantDTO> findAllItemVariants(Integer limit);

}
