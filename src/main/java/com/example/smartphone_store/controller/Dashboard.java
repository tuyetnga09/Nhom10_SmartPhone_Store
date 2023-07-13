package com.example.smartphone_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dashboard {
    @GetMapping("/dashboard")
    public String login() {
        return "dashboard/index";
    }
}
