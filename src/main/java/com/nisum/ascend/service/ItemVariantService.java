package com.nisum.ascend.service;

import com.nisum.ascend.dto.Color;
import com.nisum.ascend.dto.Size;
import com.nisum.ascend.dto.ItemVariantDTO;

import java.util.List;
import java.util.Set;

public interface ItemVariantService {

    List<ItemVariantDTO> getAllVariantsByItemMasterItemID(Long itemMasterItemID);

    ItemVariantDTO getItemVariantBySkuID(Long skuID);

    ItemVariantDTO itemVariantBasedOnItemIDAndColorAndSize(Long itemID, Color color, Size size);

    List<ItemVariantDTO> getItemVariantsBySkuIDs(Set<Long> skuIDs);

    List<ItemVariantDTO> findAllItemVariants(Integer limit);

}
