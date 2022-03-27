package com.example.eccomarce.controller;

import com.example.eccomarce.dto.ProductDTO;
import com.example.eccomarce.global.GlobalData;
import com.example.eccomarce.model.Category;
import com.example.eccomarce.model.Product;
import com.example.eccomarce.service.CategoryService;

import com.example.eccomarce.service.ProductService;
import com.example.eccomarce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.timer.TimerMBean;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminContrller {

    public static String uploadDir= System.getProperty("user.dir") + "/src/main/resources/static/productImages";//saving path of my images i will upload

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }
//---------------------------------------------------------------------------------
    @GetMapping("/admin/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }
  //TODO:This mapping below is for update items
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        }else
            return "404";
    }
    //----------------------------------------------------------------------
//Product section

    @GetMapping("/admin/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String productsAddGet(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }
//section for add items in a system include the image of item
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam ("productImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName)throws IOException{
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.Productadd(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet(@PathVariable Long id, Model model){
        Product product =productService.getProductById(id).get();
        ProductDTO productDTO =new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }

    //User Detaols....................
    @GetMapping("/admin/users")
    public String users(Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("cart", GlobalData.cart);
        return "users";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable int id){
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }
}
