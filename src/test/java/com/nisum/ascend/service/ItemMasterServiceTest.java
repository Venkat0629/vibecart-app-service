package com.nisum.ascend.service;

import com.nisum.ascend.dto.Color;
import com.nisum.ascend.dto.ItemMasterDTO;
import com.nisum.ascend.dto.Size;
import com.nisum.ascend.entities.Category;
import com.nisum.ascend.entities.ItemMaster;
import com.nisum.ascend.entities.ItemVariant;
import com.nisum.ascend.exceptions.ResourceNotFoundException;
import com.nisum.ascend.repository.ItemMasterRepository;
import com.nisum.ascend.service.impl.ItemMasterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class ItemMasterServiceTest {

    @Mock
    private ItemMasterRepository itemMasterRepository;

    @InjectMocks
    private ItemMasterServiceImpl itemMasterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_Success() {
        // Arrange
        ItemMaster itemMaster = createItemMaster();
        when(itemMasterRepository.findById(anyLong())).thenReturn(Optional.of(itemMaster));
//        when(externalServices.getOffersDataByItemID(anyLong())).thenReturn(Collections.emptyList());

        // Act
        ItemMasterDTO result = itemMasterService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getItemID());
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(itemMasterRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> itemMasterService.findById(1L));
    }

    @Test
    void testFindAll_WithLimit() {
        // Arrange
        List<ItemMaster> items = Arrays.asList(createItemMaster());
        when(itemMasterRepository.findAllByOrderByPriceDesc(PageRequest.of(0, 10)))
                .thenReturn(items);
//        when(externalServices.getOffersDataByItemID(anyLong())).thenReturn(Collections.emptyList());

        // Act
        List<ItemMasterDTO> result = itemMasterService.findAll(10, null);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testFindItemsByCatalogName_WithSorting() {
        // Arrange
        List<ItemMaster> items = Arrays.asList(createItemMaster());
        when(itemMasterRepository.findItemsByCatalogNameOrderByPriceAsc(anyString())).thenReturn(items);
//        when(externalServices.getOffersDataByItemID(anyLong())).thenReturn(Collections.emptyList());

        // Act
        List<ItemMasterDTO> result = itemMasterService.findItemsByCatalogName("Catalog", "low-high");

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllSkuIDsByItemID_Success() {
        // Arrange
        ItemMaster itemMaster = createItemMaster();
        when(itemMasterRepository.findById(anyLong())).thenReturn(Optional.of(itemMaster));

        // Act
        Map<String, List<Long>> result = itemMasterService.getAllSkuIDsByItemID(1L);

        // Assert
        assertTrue(result.containsKey("skuIDs"));
        assertFalse(result.get("skuIDs").isEmpty());
    }

    @Test
    void testGetAllSkuIDsByItemID_NotFound() {
        // Arrange
        when(itemMasterRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> itemMasterService.getAllSkuIDsByItemID(1L));
    }

    private ItemMaster createItemMaster() {
        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setItemID(1L);
        itemMaster.setItemName("Item Name");
        itemMaster.setItemDescription("Description");
        itemMaster.setPrice(100.0);
        itemMaster.setCreatedDate(LocalDate.now());
        itemMaster.setUpdatedDate(LocalDate.now());

        Category category = new Category();
        category.setCategoryID(1L);
        category.setCategoryName("Category");
        Category parentCategory = new Category();
        parentCategory.setCategoryName("Parent Category");
        category.setParentCategory(parentCategory);
        itemMaster.setCategory(category);

        ItemVariant itemVariant = new ItemVariant();
        itemVariant.setSkuID(1L);
        itemVariant.setImageURL("image.jpg");
        itemVariant.setColor(Color.RED);
        itemVariant.setSize(Size.MEDIUM);
        itemVariant.setItemMaster(itemMaster);
        itemMaster.setItemVariants(Collections.singletonList(itemVariant));

        return itemMaster;
    }
}
