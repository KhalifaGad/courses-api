package org.learn.coursesapi.category;

import org.learn.coursesapi.category.dtos.CategoryListDTO;
import org.learn.coursesapi.category.dtos.CreateCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    Category createCategory(CreateCategoryDTO input) {
        var category = new Category();
        category.setName(input.getName());
        if(input.getParentId() != null){
            var parentCategory = categoryRepository.findById(input.getParentId());
            category.setParent(parentCategory.orElseThrow());
        }
        return categoryRepository.save(category);
    }

    List<CategoryListDTO> getCategories(Long parentId) {
        List<Category> categories = new ArrayList<>(categoryRepository.findByParentId(parentId));
        List<Long> parentIds = categories.stream().map(Category::getId).toList();

        List<Category> childCategories = categoryRepository.findAll(CategorySpecs.findByParentIds(parentIds));
        return assignChildCategories(categories, childCategories);
    }

    private List<CategoryListDTO> assignChildCategories(List<Category> categories, List<Category> children){
        var parentsMap = new HashMap<Long, List<Category>>();
        children.forEach(category -> {
            var parentId = category.getParentId();
            if(parentsMap.containsKey(parentId)){
                parentsMap.get(parentId).add(category);
            } else {
                var list = new ArrayList<Category>();
                list.add(category);
                parentsMap.put(parentId, list);
            }
        });

        return categories.stream().map(category -> {
            var categoryListDTO = new CategoryListDTO();
            categoryListDTO.setId(category.getId());
            categoryListDTO.setName(category.getName());

            if(parentsMap.containsKey(category.getId())) {
                var categoryChildren = parentsMap.get(category.getId());
                categoryListDTO.setChildren(assignChildCategories(categoryChildren, children));
            } else {
                categoryListDTO.setChildren(new ArrayList<>());
            }
            return categoryListDTO;
        }).toList();
    }
}
