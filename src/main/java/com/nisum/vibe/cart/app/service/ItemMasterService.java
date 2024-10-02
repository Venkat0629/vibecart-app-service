package com.nisum.vibe.cart.app.service;

import com.nisum.vibe.cart.app.dto.ItemMasterDTO;

import java.util.List;
import java.util.Map;

public interface ItemMasterService {


    ItemMasterDTO findById(Long id);

    List<ItemMasterDTO> findAll(Integer limit, String sortingCriteria);

    Map<String,List<Long>> getAllSkuIDsByItemID(Long itemID);

    //to fetch items based on catalogName and sort them based on price
    List<ItemMasterDTO> findItemsByCatalogName(String catalogName, String sortingCriteria);

    List<ItemMasterDTO> findByCategoryName(String categoryName, String sortingCriteria);


}
