package com.tp.CRUD.service.admin.category;

import com.tp.CRUD.entity.Category;
import com.tp.CRUD.request.CategoryDto;

import java.util.List;

public interface CategoryService {

    public Category createCategory(CategoryDto categoryDto);

    public List<Category> getAllCategories();
}
