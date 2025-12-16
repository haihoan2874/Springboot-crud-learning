package com.conglt.learning.springbootboilerplate.repository;

import com.conglt.learning.springbootboilerplate.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
