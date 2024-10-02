package com.nisum.vibe.cart.app.service;

import com.nisum.vibe.cart.app.dto.CatalogDTO;
import com.nisum.vibe.cart.app.entities.Catalog;
import com.nisum.vibe.cart.app.repository.CatalogRepository;
import com.nisum.vibe.cart.app.service.impl.CatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogServiceImplJunit {

    @Mock
    private CatalogRepository catalogRepository;

    @InjectMocks
    private CatalogServiceImpl catalogService;

    private Catalog catalog1;
    private Catalog catalog2;

    @BeforeEach
    void setUp() {
        catalog1 = new Catalog(1L, "Electronics", "Electronic Items", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), LocalDate.now(), LocalDate.now(), null, "http://image1.com");
        catalog2 = new Catalog(2L, "Clothing", "Fashionable Clothes", LocalDate.now().minusDays(5), LocalDate.now().plusDays(15), LocalDate.now(), LocalDate.now(), null, "http://image2.com");
    }

    @Test
    void testFindAll() {
        // Mocking the repository response
        when(catalogRepository.findAll()).thenReturn(Arrays.asList(catalog1, catalog2));

        // Call the service method
        List<CatalogDTO> catalogDTOList = catalogService.findAll();

        // Assertions
        assertEquals(2, catalogDTOList.size());
        assertEquals("Electronics", catalogDTOList.get(0).getCatalogName());
        assertEquals("Clothing", catalogDTOList.get(1).getCatalogName());

        // Verifying interactions
        verify(catalogRepository, times(1)).findAll();
    }

    // Additional test cases for other methods can be added here
}
