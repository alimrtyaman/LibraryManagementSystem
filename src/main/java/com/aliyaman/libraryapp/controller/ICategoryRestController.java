package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.CategoryDto;

public interface ICategoryRestController {

    public CategoryDto saveCategory(CategoryDto categoryDto);

    public boolean deleteCategory(Long id);
}
