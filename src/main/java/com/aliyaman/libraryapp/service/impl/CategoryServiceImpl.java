package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.CategoryDto;
import com.aliyaman.libraryapp.entity.Category;
import com.aliyaman.libraryapp.repository.CategoryRepository;
import com.aliyaman.libraryapp.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        Category savedCategory = categoryRepository.save(category);
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(savedCategory , dto);
        return dto;
    }

    @Override
    public boolean deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new IllegalArgumentException("Category not found");
        }
        categoryRepository.delete(category.get());
        return true;
    }
}
