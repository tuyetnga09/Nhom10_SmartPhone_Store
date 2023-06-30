package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.service.ProductDetailService;
import com.example.smartphone_store.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class WebController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(Model model){
        List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
        model.addAttribute("top10PD", listTop10);

        List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
        model.addAttribute("bestSelling", listBestSelling);

        List<ProductDetail> listLineProductDetail = productDetailService.getLineProductDetail();
        model.addAttribute("lineProductDetail", listLineProductDetail);

        List<Product> productList = productService.findByStatus(0);
        model.addAttribute("productList", productList);

        return "pages/trang_chu";
    }

    @GetMapping("/login")
    public String login(){
        return "pages/login";
    }

    @GetMapping("/single-product")
    public String singleProduct(Model model){
        List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
        model.addAttribute("top10PD", listTop10);

        List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
        model.addAttribute("bestSelling", listBestSelling);

        return "pages/single_product";
    }

    @GetMapping("/product/{id}")
    public String listProduct(Model model, @PathVariable("id") Long id){
        List<ProductDetail> list = productDetailService.findProductDetailByStatusAndProductId(0, id);
        model.addAttribute("list", list);

        return "pages/list_productdetail";
    }
}
