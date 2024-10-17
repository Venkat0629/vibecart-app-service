package com.nisum.vibe.cart.app.service.impl;

import com.nisum.vibe.cart.app.dto.CatalogDTO;
import com.nisum.vibe.cart.app.dto.CatalogItemsDTO;
import com.nisum.vibe.cart.app.entities.Catalog;
import com.nisum.vibe.cart.app.entities.CatalogItems;
import com.nisum.vibe.cart.app.repository.CatalogRepository;
import com.nisum.vibe.cart.app.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {

    private static final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);
    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    @Cacheable(cacheNames = "CatalogCache")
    public List<CatalogDTO> findAll() {
        log.info("Fetching all catalogs from the database");
        List<CatalogDTO> catalogDTOs = catalogRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        log.info("Found {} catalogs", catalogDTOs.size());
        return catalogDTOs;
    }

    private CatalogDTO convertToDTO(Catalog catalog) {
        log.debug("Converting Catalog entity to DTO for Catalog ID: {}", catalog.getCatalogID());
        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setCatalogID(catalog.getCatalogID());
        catalogDTO.setCatalogName(catalog.getCatalogName());
        catalogDTO.setCatalogDescription(catalog.getCatalogDescription());
        catalogDTO.setStartDate(catalog.getStartDate());
        catalogDTO.setEndDate(catalog.getEndDate());
        catalogDTO.setCreatedDate(catalog.getCreatedDate());
        catalogDTO.setUpdatedDate(catalog.getUpdatedDate());
        catalogDTO.setImageURL(catalog.getImageURL());
        if (catalog.getCatalogItems() != null) {
            List<CatalogItemsDTO> collect = catalog.getCatalogItems().stream().map(this::convertToDTO).collect(Collectors.toList());
            catalogDTO.setCatalogItems(collect);
        }
        log.debug("Converted Catalog entity to DTO: {}", catalogDTO);
        return catalogDTO;
    }

    private CatalogItemsDTO convertToDTO(CatalogItems catalogItems) {
        log.debug("Converting CatalogItems entity to DTO for CatalogItem ID: {}", catalogItems.getCatalogItemID());
        CatalogItemsDTO catalogItemsDTO = new CatalogItemsDTO();
        catalogItemsDTO.setCatalogItemID(catalogItems.getCatalogItemID());
        catalogItemsDTO.setCatalogID(catalogItems.getCatalog().getCatalogID());
        catalogItemsDTO.setItemID(catalogItems.getItem().getItemID());
        catalogItemsDTO.setPrice(catalogItems.getPrice());
        catalogItemsDTO.setDiscount(catalogItems.getDiscount());
        catalogItemsDTO.setCreatedDate(catalogItems.getCreatedDate());
        catalogItemsDTO.setUpdatedDate(catalogItems.getUpdatedDate());
        log.debug("Converted CatalogItems entity to DTO: {}", catalogItemsDTO);
        return catalogItemsDTO;
    }
}
