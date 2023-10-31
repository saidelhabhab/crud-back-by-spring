package com.tp.CRUD.controller.admin;

import com.tp.CRUD.entity.Category;
import com.tp.CRUD.request.CategoryDto;
import com.tp.CRUD.service.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminCategoryController {

    private  final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto  categoryDto) {
        Category category = categoryService.createCategory(categoryDto);

        return  ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @GetMapping("categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


}
