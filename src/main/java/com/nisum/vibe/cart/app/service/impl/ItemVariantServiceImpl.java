package com.nisum.vibe.cart.app.service.impl;

import com.nisum.vibe.cart.app.dto.Color;
import com.nisum.vibe.cart.app.dto.ItemVariantDTO;
import com.nisum.vibe.cart.app.dto.Size;
import com.nisum.vibe.cart.app.entities.ItemVariant;
import com.nisum.vibe.cart.app.exceptions.ResourceNotFoundException;
import com.nisum.vibe.cart.app.repository.ItemVariantRepository;
import com.nisum.vibe.cart.app.service.ItemVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemVariantServiceImpl implements ItemVariantService {

    private static final Logger log = LoggerFactory.getLogger(ItemVariantServiceImpl.class);
    private final ItemVariantRepository itemVariantRepository;
    @Value("${image.fe.url}")
    private String basicUrl;

    @Autowired
    public ItemVariantServiceImpl(ItemVariantRepository itemVariantRepository) {
        this.itemVariantRepository = itemVariantRepository;
    }

    //useful for OFMS
    @Override
    public List<ItemVariantDTO> getAllVariantsByItemMasterItemID(Long itemMasterItemID) {
        log.info("Fetching all variants for itemMasterItemID: {}", itemMasterItemID);
        List<ItemVariant> allVariantsOfItemMaster = itemVariantRepository.findByItemMaster_itemID(itemMasterItemID);
        if (allVariantsOfItemMaster.isEmpty()) {
            log.error("No variants found for itemMasterItemID: {}", itemMasterItemID);
            throw new ResourceNotFoundException("There are no variants for the itemID: " + itemMasterItemID);
        }
        List<ItemVariantDTO> variantDTOList = allVariantsOfItemMaster.stream().map(this::convertToDTO).collect(Collectors.toList());
        log.info("Found {} variants for itemMasterItemID: {}", variantDTOList.size(), itemMasterItemID);
        return variantDTOList;
    }

    //useful for OFMS, no change needed
    @Override
    public ItemVariantDTO getItemVariantBySkuID(Long skuID) {
        log.info("Fetching item variant for SKU ID: {}", skuID);
        ItemVariantDTO itemVariantDTO = itemVariantRepository.findById(skuID).map(this::convertToDTO).orElseThrow(() -> {
            log.error("No item found with SKU ID: {}", skuID);
            return new ResourceNotFoundException("No item found with the skuID: " + skuID);
        });
        log.info("Found item variant for SKU ID: {}", skuID);
        return itemVariantDTO;
    }

    @Override
    public List<ItemVariantDTO> findAllItemVariants(Integer limit) {
        if (limit != null) {
            List<ItemVariant> limitedSkuItems = itemVariantRepository.findLimitedSkuItems(limit);
            return limitedSkuItems.stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        return itemVariantRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //primary method
    @Override
    public ItemVariantDTO itemVariantBasedOnItemIDAndColorAndSize(Long itemID, Color color, Size size) {
        log.info("Fetching item variant for itemID: {}, color: {}, size: {}", itemID, color, size);
        List<ItemVariant> byItemMasterItemID = itemVariantRepository.findByItemMaster_ItemIDAndColorAndSize(itemID, color, size);
        Optional<ItemVariant> first = byItemMasterItemID.stream().filter(itemVariant -> itemVariant.getColor().equals(color) && itemVariant.getSize().equals(size)).findFirst();

        if (!first.isPresent()) {
            log.error("Item not available for itemID: {}, color: {}, size: {}", itemID, color, size);
            throw new ResourceNotFoundException("Item not available for the color " + color + " in " + size + " size");
        }
        ItemVariantDTO itemVariantDTO = convertToDTO(first.get());
        log.info("Found item variant: {}", itemVariantDTO);
        return itemVariantDTO;
    }

    //for inventory
    @Override
    public List<ItemVariantDTO> getItemVariantsBySkuIDs(Set<Long> skuIDs) {
        return skuIDs.stream().map(this::getItemVariantBySkuID).collect(Collectors.toList());
    }


    private ItemVariantDTO convertToDTO(ItemVariant itemVariant) {
        log.debug("Converting item variant to DTO: {}", itemVariant);
        ItemVariantDTO itemVariantDTO = new ItemVariantDTO();
        itemVariantDTO.setSkuID(itemVariant.getSkuID());
        itemVariantDTO.setColor(itemVariant.getColor());
        itemVariantDTO.setItemID(itemVariant.getItemMaster().getItemID());
        itemVariantDTO.setSize(itemVariant.getSize());
        itemVariantDTO.setItemDescription(itemVariant.getItemMaster().getItemDescription());
        itemVariantDTO.setItemName(itemVariant.getItemMaster().getItemName());
        itemVariantDTO.setPrice(itemVariant.getItemMaster().getPrice());
        itemVariantDTO.setAvailableColors(itemVariant.getItemMaster().getItemVariants().stream().map(itemVariant1 -> itemVariant1.getColor() + "").distinct().collect(Collectors.toList()));
        itemVariantDTO.setAvailableSizes(itemVariant.getItemMaster().getItemVariants().stream().map(itemVariant1 -> itemVariant1.getSize() + "").distinct().collect(Collectors.toList()));
        itemVariantDTO.setImageURLs(itemVariant.getItemMaster().getItemVariants().stream().map(itemVariant1 -> basicUrl + "/" + itemVariant1.getImageURL()).distinct().collect(Collectors.toList()));
        itemVariantDTO.setCategoryName(itemVariant.getItemMaster().getCategory().getCategoryName());
        itemVariantDTO.setParentCategoryName(itemVariant.getItemMaster().getCategory().getParentCategory().getCategoryName());
        itemVariantDTO.setImageURL(basicUrl + "/" + itemVariant.getImageURL());
        log.debug("Converted DTO: {}", itemVariantDTO);
        return itemVariantDTO;
    }

}
