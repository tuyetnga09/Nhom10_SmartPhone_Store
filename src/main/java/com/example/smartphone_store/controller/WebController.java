package com.example.smartphone_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/home")
    public String home(){
        return "pages/trang_chu";
    }
}
