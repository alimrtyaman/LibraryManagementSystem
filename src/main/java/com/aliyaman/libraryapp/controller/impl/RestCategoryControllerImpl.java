package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.ICategoryRestController;
import com.aliyaman.libraryapp.dto.CategoryDto;
import com.aliyaman.libraryapp.service.ICategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/category")
public class RestCategoryControllerImpl implements ICategoryRestController {

    private final ICategoryService categoryService;

    public RestCategoryControllerImpl(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    @Override
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
