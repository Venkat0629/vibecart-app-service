package com.nisum.vibe.cart.app.service.impl;

import com.nisum.vibe.cart.app.dto.CategoryDTO;
import com.nisum.vibe.cart.app.entities.Category;
import com.nisum.vibe.cart.app.repository.CategoryRepository;
import com.nisum.vibe.cart.app.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Cacheable(cacheNames = "CategoryCache")
    public List<CategoryDTO> findAll() {
        log.info("Fetching all categories from the database");
        List<CategoryDTO> categoryDTOs = categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        log.info("Found {} categories", categoryDTOs.size());
        return categoryDTOs;
    }

    private CategoryDTO convertToDTO(Category category) {
        log.debug("Converting Category entity to DTO for Category ID: {}", category.getCategoryID());
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryID(category.getCategoryID());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setParentCategoryID(category.getParentCategory() != null ? category.getParentCategory().getCategoryID() : null);
        categoryDTO.setCreatedDate(category.getCreatedDate());
        categoryDTO.setUpdatedDate(category.getUpdatedDate());
        if (category.getSubCategories() != null) {
            List<CategoryDTO> subcategories = category.getSubCategories().stream().map(this::convertToDTO).collect(Collectors.toList());
            categoryDTO.setSubCategories(subcategories);
        }
        log.debug("Converted Category entity to DTO: {}", categoryDTO);
        return categoryDTO;
    }

}
