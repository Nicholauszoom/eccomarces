package com.example.eccomarce.repository;

import com.example.eccomarce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//interact with database
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
