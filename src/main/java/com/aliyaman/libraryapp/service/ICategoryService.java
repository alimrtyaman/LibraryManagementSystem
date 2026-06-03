package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    public CategoryDto saveCategory(CategoryDto categoryDto);

    public boolean deleteCategory(Long id);

    public List<CategoryDto> getAllCategories();;

}
