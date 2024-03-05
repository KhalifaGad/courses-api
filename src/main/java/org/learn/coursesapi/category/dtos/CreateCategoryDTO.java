package org.learn.coursesapi.category.dtos;

import lombok.Data;

@Data
public class CreateCategoryDTO {
    private String name;
    private Long parentId;
}
