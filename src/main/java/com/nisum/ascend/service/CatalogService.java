package com.nisum.ascend.service;

import com.nisum.ascend.dto.CatalogDTO;

import java.util.List;

public interface CatalogService {
    List<CatalogDTO> findAll();
}
