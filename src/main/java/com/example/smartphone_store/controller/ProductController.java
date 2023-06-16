package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.service.impl.ImagesService;
import com.example.smartphone_store.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImagesService imagesService;

    @GetMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("list_images", this.imagesService.findAll(0));
        model.addAttribute("list_product", this.productService.selectByStatus(0));
        return "product/index";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute @Valid Product product, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("list_images", this.imagesService.findAll(0));
            model.addAttribute("list_product", this.productService.selectByStatus(0));
            return "product/index";
        }
        this.productService.save(product);
        return "redirect:/product/index";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable Long id, Model model){
        model.addAttribute("product", this.productService.findById(id));
        model.addAttribute("list_images", this.imagesService.findAll(0));
        model.addAttribute("list_product", this.productService.selectByStatus(0));
        return "product/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("product", this.productService.findById(id));
        model.addAttribute("list_images", this.imagesService.findAll(0));
        model.addAttribute("list_product", this.productService.selectByStatus(0));
        return "product/edit";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@ModelAttribute Product product){
        product.setDateUpdate(new Date(new java.util.Date().getTime()));
        this.productService.update(product);
        return "redirect:/product/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.productService.delete(id);
        return "redirect:/product/index";
    }

}
