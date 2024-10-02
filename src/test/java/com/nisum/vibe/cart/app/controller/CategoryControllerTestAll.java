package com.nisum.vibe.cart.app.controller;

import com.nisum.vibe.cart.app.dto.CategoryDTO;
import com.nisum.vibe.cart.app.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryControllerTestAll {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories_ReturnsCategories() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Electronics", "Electronics category", null, Collections.emptyList(), LocalDate.now(), LocalDate.now());
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categoryDTOs.add(categoryDTO);
        when(categoryService.findAll()).thenReturn(categoryDTOs);

        // Act
        ResponseEntity<List<CategoryDTO>> response = categoryController.getAllCategories();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(categoryDTOs, response.getBody());
    }

    @Test
    public void testGetAllCategories_ReturnsNoContent() {
        // Arrange
        when(categoryService.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<CategoryDTO>> response = categoryController.getAllCategories();

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        assertEquals(null, response.getBody());
    }
}
