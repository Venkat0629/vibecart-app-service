package com.nisum.ascend.controller;

import com.nisum.ascend.dto.CatalogDTO;
import com.nisum.ascend.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Catalogs", description = "Operations related to catalogs and their items") // Tag for grouping related endpoints
@RestController
@RequestMapping("/vibecart/ecom/catalogs")
public class CatalogController {

    private static final Logger log= LoggerFactory.getLogger(CatalogController.class);
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Operation(
            summary = "Get all catalogs",
            description = "Fetches all catalogs present in the database along with their respective catalog items.",
            tags = { "Catalogs" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of catalogs",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CatalogDTO.class))),
            @ApiResponse(responseCode = "204", description = "No catalogs found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CatalogDTO>> getAllCatalogs() {
        List<CatalogDTO> catalogs = catalogService.findAll();
        log.info("list of catalogs: {}",catalogs);
        if (catalogs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(catalogs);
    }


}
