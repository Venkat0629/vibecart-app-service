package com.nisum.vibe.cart.app.controller;

import com.nisum.vibe.cart.app.dto.ItemMasterDTO;
import com.nisum.vibe.cart.app.service.ItemMasterService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemMasterController.class)
public class ItemMasterControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ItemMasterService itemMasterService;

    @Value("${image.upload.dir}")
    private String uploadDir="E:/images";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ItemMasterController(itemMasterService)).build();
    }

    @Test
    public void testGetAllItems_Success() throws Exception {
        // Arrange
        List<ItemMasterDTO> items = Collections.singletonList(createItemMasterDTO());
        when(itemMasterService.findAll(any(Integer.class), anyString())).thenReturn(items);

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items")
                        .param("limit", "10")
                        .param("sortingCriteria", "low-high"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].itemID").value(1));
    }

    @Test
    public void testGetAllItems_NoContent() throws Exception {
        // Arrange
        when(itemMasterService.findAll(any(Integer.class), anyString())).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetItemById_Success() throws Exception {
        // Arrange
        ItemMasterDTO itemMasterDTO = createItemMasterDTO();
        when(itemMasterService.findById(anyLong())).thenReturn(itemMasterDTO);

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items/item/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemID").value(1));
    }

    @Test
    public void testGetItemById_NotFound() throws Exception {
        // Arrange
        when(itemMasterService.findById(anyLong())).thenThrow(new RuntimeException("Item not found"));

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items/item/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetItemsByCatalogName_Success() throws Exception {
        // Arrange
        List<ItemMasterDTO> items = Collections.singletonList(createItemMasterDTO());
        when(itemMasterService.findItemsByCatalogName(anyString(), anyString())).thenReturn(items);

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items/catalog/{catalogName}", "Catalog")
                        .param("sortingCriteria", "low-high"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].itemID").value(1));
    }

    @Test
    public void testGetItemsByCategoryName_Success() throws Exception {
        // Arrange
        List<ItemMasterDTO> items = Collections.singletonList(createItemMasterDTO());
        when(itemMasterService.findByCategoryName(anyString(), anyString())).thenReturn(items);

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items/category/{categoryName}", "Category")
                        .param("sortingCriteria", "high-low"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].itemID").value(1));
    }

    @Test
    public void testGetAllSkuIDsByItemID_Success() throws Exception {
        // Arrange
        Map<String, List<Long>> skuIDs = Collections.singletonMap("skuIDs", Collections.singletonList(1L));
        when(itemMasterService.getAllSkuIDsByItemID(anyLong())).thenReturn(skuIDs);

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items/item/{itemID}/skuIDs", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.skuIDs[0]").value(1));
    }

    @Test
    public void testGetImage_Success() throws Exception {
        // Arrange
        String filename = "image.jpg";
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        when(resource.exists()).thenReturn(true);
        when(resource.isReadable()).thenReturn(true);

        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items/images/{filename}", filename))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\""));
    }

    @Test
    public void testGetImage_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/vibecart/ecom/items/images/{filename}", "nonexistent.jpg"))
                .andExpect(status().isNotFound());
    }

    private ItemMasterDTO createItemMasterDTO() {
        ItemMasterDTO dto = new ItemMasterDTO();
        dto.setItemID(1L);
        dto.setItemName("Item Name");
        dto.setItemDescription("Description");
        dto.setPrice(100.0);
        return dto;
    }
}
