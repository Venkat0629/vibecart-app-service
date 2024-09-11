package com.nisum.ascend.service;

import com.nisum.ascend.dto.CategoryDTO;
import com.nisum.ascend.entities.Category;
import com.nisum.ascend.repository.CategoryRepository;
import com.nisum.ascend.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTestAll {

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll_ReturnsCategories() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Electronics category", null, new ArrayList<>(), LocalDate.now(), LocalDate.now());
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<CategoryDTO> result = categoryServiceImpl.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Electronics", result.get(0).getCategoryName());
    }

    @Test
    public void testFindAll_ReturnsEmptyList() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<CategoryDTO> result = categoryServiceImpl.findAll();

        // Assert
        assertEquals(0, result.size());
    }
}
