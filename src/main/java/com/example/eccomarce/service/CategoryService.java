package com.example.eccomarce.service;

import com.example.eccomarce.model.Category;
import com.example.eccomarce.repository.CategoryRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
     CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public void removeCategoryById(int id){
        categoryRepository.deleteById(id);
    }
    //this paragraph is writed to find specific item and update
    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
    }

}
