package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.ProductDetails;
import com.example.smartphone_store.service.BatteryService;
import com.example.smartphone_store.service.CapacityService;
import com.example.smartphone_store.service.CategoryService;
import com.example.smartphone_store.service.ChipService;
import com.example.smartphone_store.service.ColorService;
import com.example.smartphone_store.service.ManufactureService;
import com.example.smartphone_store.service.ProductDetailService;
import com.example.smartphone_store.service.RamService;
import com.example.smartphone_store.service.ScreenService;
import com.example.smartphone_store.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productDetails/")
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
    @Autowired
    private ChipService chipService;
    @Autowired
    private RamService ramService;
    @Autowired
    private ScreenService screenService;

    @GetMapping("display")
    public String getAllProductDetails(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<ProductDetails> productDetails = productDetailService.getPage(pageNo, 5);
        model.addAttribute("productDetails", productDetails.getContent());
        model.addAttribute("productDetailPages", productDetails.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("proDetail", new ProductDetails());
        model.addAttribute("capacity", capacityService.getAll());
        model.addAttribute("color", colorService.getAll());
        return "/productDetails/productDetails";
    }

}
