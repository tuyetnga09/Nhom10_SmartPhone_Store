package com.example.smartphone_store.controller;

import com.example.smartphone_store.service.impl.BatterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/battery")
public class BatteryController {

    @Autowired
    private BatterServiceImpl batterService;

}
