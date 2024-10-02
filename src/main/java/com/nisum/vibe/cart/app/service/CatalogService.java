package com.nisum.vibe.cart.app.service;

import com.nisum.vibe.cart.app.dto.CatalogDTO;

import java.util.List;

public interface CatalogService {
    List<CatalogDTO> findAll();
}
