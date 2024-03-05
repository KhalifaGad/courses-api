package org.learn.coursesapi.category;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

class CategorySpecs {
    private CategorySpecs() {
    }

    static Specification<Category> findByParentIds(List<Long> parentIds) {
        return (root, query, builder) -> root.get(Category_.parentId).in(parentIds);
    }
}
