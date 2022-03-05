package com.example.eccomarce.controller;

import com.example.eccomarce.global.GlobalData;
import com.example.eccomarce.model.Product;
import com.example.eccomarce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public  String addToCart(@PathVariable Long id){
        GlobalData.cart.add(productService.getProductById(id).get());
       return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cart", GlobalData.cart);
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice));
        return "cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String removeCart(@PathVariable int index){
        GlobalData.cart.remove(index);
     return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String goToCheckout(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
     //   model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice));
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice));
        return "checkout";
    }
}
