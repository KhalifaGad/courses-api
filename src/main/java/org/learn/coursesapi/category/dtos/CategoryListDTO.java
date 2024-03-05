package org.learn.coursesapi.category.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CategoryListDTO {
    private Long id;
    private String name;
    List<CategoryListDTO> children;
}
