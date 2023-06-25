package com.example.smartphone_store.controller;

import com.example.smartphone_store.DAO.ImagesDAO;
import com.example.smartphone_store.entity.Images;
import com.example.smartphone_store.service.impl.ImagesService;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping(value = "/images")
public class ImagesController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @Autowired
    private ImagesService service;

    @GetMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("image", new ImagesDAO());
        model.addAttribute("list", this.service.findAll(0));
        return "images/index";
    }

    @PostMapping(value = "/store")
    public String store(@RequestParam(name = "fileImages") MultipartFile fileImages,
                        @RequestParam(name = "describe") String describe,
                        @RequestParam(name = "personCreate") String personCreate,
                        Model model) throws IOException {
        saveImage(fileImages);
        Images image = new Images();
        image.setLinkImage("/images/" + fileImages.getOriginalFilename());
        image.setNameImage(fileImages.getOriginalFilename());
        image.setDescribe(describe);
        image.setPersonCreate(personCreate);
        this.service.save(image);
        return "redirect:/images/index";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable Long id) {

        model.addAttribute("list", this.service.findAll(0));
        return "images/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
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

    public void saveImage(MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        File fileImages = new File(fileNameAndPath.toString());
        if (!fileImages.exists()) {
            FileOutputStream fos = new FileOutputStream(fileImages);
            fos.write(file.getBytes());
            fos.close();
        } else {
            Files.write(fileNameAndPath, file.getBytes());
        }
    }

}
