package com.nisum.ascend.service;

import com.nisum.ascend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
}
