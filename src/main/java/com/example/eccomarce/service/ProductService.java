package com.example.eccomarce.service;

import com.example.eccomarce.model.Category;
import com.example.eccomarce.model.Product;
import com.example.eccomarce.repository.CategoryRepository;
import com.example.eccomarce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public void Productadd(Product product){
        productRepository.save(product);
    }
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategory_Id(id);
    }

}
