package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.CategoryDto;
import com.aliyaman.libraryapp.dto.LoanDto;

import java.util.List;

public interface ICategoryRestController {

    public CategoryDto saveCategory(CategoryDto categoryDto);

    public boolean deleteCategory(Long id);

    public List<CategoryDto> getAllCategories();



}
