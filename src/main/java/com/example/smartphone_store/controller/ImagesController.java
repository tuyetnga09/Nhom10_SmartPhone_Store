package com.example.smartphone_store.controller;

import com.example.smartphone_store.DAO.ImagesDAO;
import com.example.smartphone_store.entity.Images;
import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.service.impl.ImagesService;
import com.example.smartphone_store.service.impl.ProductService;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/images")
public class ImagesController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/index")
    public String index(Model model, @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex) {
        Pageable page = PageRequest.of(pageIndex, 5);
        model.addAttribute("image", new ImagesDAO());
        model.addAttribute("list_product", this.productService.findByStatus(0));
        model.addAttribute("list_images", this.imagesService.selectAll(0, page).getContent());
        model.addAttribute("pageSize", this.imagesService.selectAll(0, page).getTotalPages());
        model.addAttribute("pageNumber", pageIndex);
        return "images/index";
    }

    @PostMapping(value = "/store")
    public String store(@RequestParam(name = "fileImages") MultipartFile[] fileImages,
                        @RequestParam(name = "describe") String describe,
                        @RequestParam(name = "idProduct") Product idProduct,
                        @RequestParam(name = "personCreate") String personCreate,
                        Model model) throws IOException {
        for (MultipartFile file: fileImages) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            File fileImage = new File(fileNameAndPath.toString());
            Files.write(fileNameAndPath, file.getBytes());
            Images image = new Images();
            image.setLinkImage("/images/" + file.getOriginalFilename());
            image.setNameImage(file.getOriginalFilename());
            image.setDescribe(describe);
            image.setIdProduct(idProduct);
            image.setPersonCreate(personCreate);
            this.imagesService.save(image);
        }
        return "redirect:/images/index";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable Long id) {
        return "images/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.imagesService.delete(id);
        return "redirect:/images/index";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("excelFile") MultipartFile excelFile, Model model) {
        try {
            InputStream inputStream = excelFile.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Path fileNamePath = Paths.get(UPLOAD_DIRECTORY, row.getCell(0).getStringCellValue());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "images/index";
    }

    public void saveImage(MultipartFile[] file) throws IOException {

    }

}
