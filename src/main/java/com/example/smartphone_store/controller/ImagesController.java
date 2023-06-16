package com.example.smartphone_store.controller;

import com.example.smartphone_store.DAO.ImagesDAO;
import com.example.smartphone_store.entity.Images;
import com.example.smartphone_store.service.impl.ImagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public String store(@Valid ImagesDAO imagesDAO, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("image", imagesDAO);
            model.addAttribute("list", this.service.findAll(0));
            return "images/index";
        }
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, imagesDAO.getFileImages().getOriginalFilename());
        Files.write(fileNameAndPath, imagesDAO.getFileImages().getBytes());
        Images image = new Images();
        image.setLinkImage("/images/" + imagesDAO.getFileImages().getOriginalFilename());
        image.setNameImage(imagesDAO.getFileImages().getOriginalFilename());
        image.setDescribe(imagesDAO.getDescribe());
        image.setPersonCreate(imagesDAO.getPersonCreate());
        this.service.save(image);
        return "redirect:/images/index";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable Long id){

        model.addAttribute("list", this.service.findAll(0));
        return "images/index";
    }



}
