package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.CategoryDto;

public interface ICategoryService {

    public CategoryDto saveCategory(CategoryDto categoryDto);

    public boolean deleteCategory(Long id);
}
