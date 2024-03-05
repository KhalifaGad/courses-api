package org.learn.coursesapi.category;

import org.learn.coursesapi.category.dtos.CategoryListDTO;
import org.learn.coursesapi.category.dtos.CreateCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    Category getCategories(@RequestBody CreateCategoryDTO input) {
        return categoryService.createCategory(input);
    }

    @RequestMapping("/categories")
    List<CategoryListDTO> getCategories(@RequestParam(value = "parentId", required = false) Long parentId) {
        return categoryService.getCategories(parentId);
    }
}
