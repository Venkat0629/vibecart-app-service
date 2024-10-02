package com.nisum.vibe.cart.app.service.impl;

import com.nisum.vibe.cart.app.dto.ItemMasterDTO;
import com.nisum.vibe.cart.app.entities.ItemMaster;
import com.nisum.vibe.cart.app.entities.ItemVariant;
import com.nisum.vibe.cart.app.exceptions.ResourceNotFoundException;
import com.nisum.vibe.cart.app.repository.ItemMasterRepository;
import com.nisum.vibe.cart.app.service.ItemMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemMasterServiceImpl implements ItemMasterService {

    private static final Logger log = LoggerFactory.getLogger(ItemMasterServiceImpl.class);
    private final ItemMasterRepository itemMasterRepository;

    @Autowired
    public ItemMasterServiceImpl(ItemMasterRepository itemMasterRepository) {
        this.itemMasterRepository = itemMasterRepository;
    }


    @Override
    public ItemMasterDTO findById(Long id) {
        log.info("Fetching item with ID: {}", id);
        ItemMaster itemMaster = itemMasterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));
        log.info("Found item: {}", itemMaster);
        return convertToDTO(itemMaster);
    }

    @Override
    public List<ItemMasterDTO> findAll(Integer limit, String sortingCriteria) {
        log.info("Fetching all items with limit: {} and sortingCriteria: {}", limit, sortingCriteria);
        List<ItemMasterDTO> items;
        if (limit != null) {
            Pageable pageable = PageRequest.of(0, limit);
            return itemMasterRepository.findAllByOrderByPriceDesc(pageable).stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        if (sortingCriteria != null) {
            if (sortingCriteria.equalsIgnoreCase("low-high")) {
                return itemMasterRepository.findAllByOrderByPriceAsc().stream().map(this::convertToDTO).collect(Collectors.toList());
            } else if (sortingCriteria.equalsIgnoreCase("high-low")) {
                return itemMasterRepository.findAllByOrderByPriceDesc().stream().map(this::convertToDTO).collect(Collectors.toList());
            }
        }
        List<ItemMasterDTO> itemsList = itemMasterRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        log.info("Fetched {} items", itemsList.size());
        return itemsList;
    }


    //to fetch items based on catalogName
    @Override
    public List<ItemMasterDTO> findItemsByCatalogName(String catalogName, String sortingCriteria) {
        log.info("Fetching items for catalog: {} with sorting criteria: {}", catalogName, sortingCriteria);
        if(sortingCriteria!=null){
            if(sortingCriteria.equalsIgnoreCase("low-high")){
                List<ItemMaster> itemsByCatalogNameOrderByPriceAsc = itemMasterRepository.findItemsByCatalogNameOrderByPriceAsc(catalogName);
                if(!itemsByCatalogNameOrderByPriceAsc.isEmpty()){
                    return itemsByCatalogNameOrderByPriceAsc.stream().map(this::convertToDTO).collect(Collectors.toList());
                }
            }
            else if(sortingCriteria.equalsIgnoreCase("high-low")){
                List<ItemMaster> itemsByCatalogNameOrderByPriceDesc = itemMasterRepository.findItemsByCatalogNameOrderByPriceDesc(catalogName);
                if(!itemsByCatalogNameOrderByPriceDesc.isEmpty()) {
                    return itemsByCatalogNameOrderByPriceDesc.stream().map(this::convertToDTO).collect(Collectors.toList());
                }
            }
        }
        List<ItemMaster> itemsByCatalogName = itemMasterRepository.findItemsByCatalogName(catalogName);
        log.info("Fetched {} items for catalog: {}", itemsByCatalogName.size(), catalogName);
        return itemsByCatalogName.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //to get all sku IDs related to item id
    @Override
    public Map<String,List<Long>> getAllSkuIDsByItemID(Long itemID){
        log.info("Fetching SKU IDs for item ID: {}", itemID);
        Map<String,List<Long>> skuIDs=new HashMap<>();
        Optional<ItemMaster> byId = itemMasterRepository.findById(itemID);
        if(byId.isPresent()){
            List<Long> skuIDList = byId.get().getItemVariants().stream().map(ItemVariant::getSkuID).collect(Collectors.toList());
            skuIDs.put("skuIDs",skuIDList);
            log.info("Found SKU IDs for item ID {}: {}", itemID, skuIDList);
        }
        else {
            log.error("No items found with the item ID: {}", itemID);
            throw new ResourceNotFoundException("No items found withe the itemID: "+itemID);
        }
        return skuIDs;
    }

    //to find items based on category name, can also be used for sorting based on price
    @Override
    public List<ItemMasterDTO> findByCategoryName(String categoryName, String sortingCriteria) {
        log.info("Fetching items for category: {} with sorting criteria: {}", categoryName, sortingCriteria);
        if (sortingCriteria != null) {
            if (sortingCriteria.equalsIgnoreCase("low-high")) {
                return itemMasterRepository.findByCategory_CategoryNameOrderByPriceAsc(categoryName).stream().map(this::convertToDTO).collect(Collectors.toList());
            } else if (sortingCriteria.equalsIgnoreCase("high-low")) {
                return itemMasterRepository.findByCategory_CategoryNameOrderByPriceDesc(categoryName).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
        }
        List<ItemMaster> itemsByCategoryName = itemMasterRepository.findItemsByCategoryName(categoryName);
        log.info("Fetched {} items for category: {}", itemsByCategoryName.size(), categoryName);
        return itemsByCategoryName.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Value("${image.fe.url}")
    private String basicUrl;

    private ItemMasterDTO convertToDTO(ItemMaster itemMaster) {
        log.debug("Converting item entity to DTO: {}", itemMaster);
        ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
        itemMasterDTO.setItemID(itemMaster.getItemID());
        itemMasterDTO.setItemName(itemMaster.getItemName());
        itemMasterDTO.setItemDescription(itemMaster.getItemDescription());
        itemMasterDTO.setCategoryID(itemMaster.getCategory().getCategoryID());
        itemMasterDTO.setPrice(itemMaster.getPrice());
        itemMasterDTO.setImageURLs(itemMaster.getItemVariants().stream().map(itemVariant -> basicUrl + "/" + itemVariant.getImageURL()).distinct().collect(Collectors.toList()));
        itemMasterDTO.setAvailableColors(itemMaster.getItemVariants().stream().map(itemVariant -> itemVariant.getColor() + "").distinct().collect(Collectors.toList()));
        itemMasterDTO.setAvailableSizes(itemMaster.getItemVariants().stream().map(itemVariant -> itemVariant.getSize() + "").distinct().collect(Collectors.toList()));
        itemMasterDTO.setCreatedDate(itemMaster.getCreatedDate());
        itemMasterDTO.setUpdatedDate(itemMaster.getUpdatedDate());
        itemMasterDTO.setCategoryName(itemMaster.getCategory().getCategoryName());
        itemMasterDTO.setParentCategoryName(itemMaster.getCategory().getParentCategory().getCategoryName());
        log.debug("Converted item DTO: {}", itemMasterDTO);
        return itemMasterDTO;
    }

}
