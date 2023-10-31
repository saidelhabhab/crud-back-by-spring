package com.tp.CRUD.service.admin.category;

import com.tp.CRUD.entity.Category;
import com.tp.CRUD.repository.CategoryRepo;
import com.tp.CRUD.request.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {

    private  final CategoryRepo categoryRepo;


    @Override
    public Category createCategory(CategoryDto categoryDto){

        Category category = new Category();

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
