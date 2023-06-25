package com.example.smartphone_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/home")
    public String home(){
        return "pages/trang_chu";
    }

    @GetMapping("/login")
    public String login(){
        return "pages/login";
    }

    @GetMapping("/single-product")
    public String singleProduct(){
        return "pages/single_product";
    }
}
