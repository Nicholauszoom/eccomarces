package com.example.eccomarce.controller;

import com.example.eccomarce.global.GlobalData;
import com.example.eccomarce.model.Role;
import com.example.eccomarce.model.User;
import com.example.eccomarce.repository.RoleRepository;
import com.example.eccomarce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
        UserRepository userRepository;
    @Autowired
        RoleRepository roleRepository;
    @GetMapping("/register")
    public String registerGet(){
        return "register.html";
    }
    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login.html";
    }
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user , HttpServletRequest request) throws ServletException {
    String password=user.getPassword();
    user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
      //  request.login(user.getEmail(), password);// NOTE: If u want a user after register the system to allow to be login direct
        return "redirect:/register";
    }
}
