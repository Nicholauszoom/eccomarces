//package com.example.eccomarce.controller;
//
//import com.example.eccomarce.repository.RoleRepository;
//import com.example.eccomarce.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class LoginController {
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
//
//    @GetMapping("/login")
//    public String loginGet(){
//        return "login";
//    }
//    @GetMapping("/register")
//    public String registration(){
//        return "register";
//    }
//}
