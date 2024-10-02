package com.nisum.vibe.cart.app.service;

import com.nisum.vibe.cart.app.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
}
