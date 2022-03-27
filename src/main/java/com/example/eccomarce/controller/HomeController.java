package com.example.eccomarce.controller;

import com.example.eccomarce.global.GlobalData;
import com.example.eccomarce.service.CategoryService;
import com.example.eccomarce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/","/home" })
        public String home(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getAllProduct());
        return "index";
    }
   /* @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "home";
    }*/

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(@PathVariable int id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("product", productService.getProductById(id).get());
        return "viewProduct";
    }

}
