package com.nisum.vibe.cart.app.controller;

import com.nisum.vibe.cart.app.dto.CatalogDTO;
import com.nisum.vibe.cart.app.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vibe-cart/app/catalogs")
public class CatalogController {

    private static final Logger log= LoggerFactory.getLogger(CatalogController.class);
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

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
