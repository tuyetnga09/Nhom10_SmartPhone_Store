package com.example.smartphone_store.controller;

import com.example.smartphone_store.service.BatteryService;
import com.example.smartphone_store.service.CapacityService;
import com.example.smartphone_store.service.CategoryService;
import com.example.smartphone_store.service.ColorService;
import com.example.smartphone_store.service.ManufactureService;
import com.example.smartphone_store.service.ProductDetailService;
import com.example.smartphone_store.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-details/")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CapacityService capacityService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ManufactureService manufactureService;
    @Autowired
    private BatteryService batteryService;


}
