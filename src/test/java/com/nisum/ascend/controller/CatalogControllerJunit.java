package com.nisum.ascend.controller;

import com.nisum.ascend.dto.CatalogDTO;
import com.nisum.ascend.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogController.class)
@ExtendWith(MockitoExtension.class)
class CatalogControllerJunit {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

    private CatalogDTO catalogDTO;

    @BeforeEach
    void setUp() {
        // Initialize the CatalogDTO object and set values using setters
        catalogDTO = new CatalogDTO();
        catalogDTO.setCatalogID(1L);
        catalogDTO.setCatalogName("Electronics");
        catalogDTO.setCatalogDescription("Electronic Items");
        catalogDTO.setStartDate(LocalDate.now().minusDays(10));
        catalogDTO.setEndDate(LocalDate.now().plusDays(10));
        catalogDTO.setCreatedDate(LocalDate.now());
        catalogDTO.setUpdatedDate(LocalDate.now());
        catalogDTO.setImageURL("http://image1.com");
        catalogDTO.setCatalogItems(new ArrayList<>()); // Set an empty list for catalog items
    }

    @Test
    void testGetAllCatalogs() throws Exception {
        // Prepare a list containing the mock CatalogDTO
        List<CatalogDTO> catalogList = new ArrayList<>();
        catalogList.add(catalogDTO);

        // Mock the findAll method of the service to return the catalog list
        when(catalogService.findAll()).thenReturn(catalogList);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/vibecart/ecom/catalogs") // Ensure the URL matches the controller mapping
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Check for HTTP 200 status
                .andExpect(jsonPath("$.size()").value(1)) // Validate the size of the returned list
                .andExpect(jsonPath("$[0].catalogID").value(1)) // Check the catalogID of the first item
                .andExpect(jsonPath("$[0].catalogName").value("Electronics")); // Check catalog name for additional verification

        // Verify that the findAll method was called exactly once
        verify(catalogService, times(1)).findAll();
    }

    @Test
    void testGetAllCatalogsReturnsNoContent() throws Exception {
        // Mock the findAll method of the service to return an empty list
        when(catalogService.findAll()).thenReturn(new ArrayList<>());

        // Perform the GET request and validate the response for no content
        mockMvc.perform(get("/vibecart/ecom/catalogs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()); // Check for HTTP 204 status

        // Verify that the findAll method was called exactly once
        verify(catalogService, times(1)).findAll();
    }
}
