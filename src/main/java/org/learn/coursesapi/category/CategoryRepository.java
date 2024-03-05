package org.learn.coursesapi.category;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface CategoryRepository extends CrudRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    List<Category> findByParentId(Long parentId);
}
