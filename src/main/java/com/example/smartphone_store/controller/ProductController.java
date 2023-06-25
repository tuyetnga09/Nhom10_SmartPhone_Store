package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.service.impl.ImagesService;
import com.example.smartphone_store.service.impl.ProductService;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    public String store(Model model, @ModelAttribute @Valid Product product, BindingResult result) {
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

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "excelFile") MultipartFile file){
        try{
            InputStream inputStream = file.getInputStream();
            Workbook workbook =  new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet){
                if (row.getRowNum() == 0){
                    continue;
                }
                Product product = new Product();
                product.setCode(row.getCell(0).getStringCellValue());
                product.setName(row.getCell(1).getStringCellValue());
                product.setImportPrice(row.getCell(2).getNumericCellValue());
                product.setPrice(row.getCell(3).getNumericCellValue());
                product.setQuantity((int)row.getCell(4).getNumericCellValue());
                product.setPersonCreate(row.getCell(5).getStringCellValue());
                product.setIdImages(imagesService.findById((long)row.getCell(6).getNumericCellValue()));
                productService.save(product);
            }
            workbook.close();
            return "redirect:/product/index";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
