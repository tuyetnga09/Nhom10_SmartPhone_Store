package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Color;
import com.example.smartphone_store.service.ColorService;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.time.LocalDate;

@Controller
@RequestMapping("/color/")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("")
    public String getAll(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page){
        Page<Color> colorPage = colorService.paging(page, 5);
        model.addAttribute("colors", colorPage.getContent());
        model.addAttribute("pageProduct", colorPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("cl", new Color());
        return "color/color";
    }

    @GetMapping("view-add")
    public String viewAdd(Model model){
        model.addAttribute("cl", new Color());
        return "color/color-view-add";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdate(@PathVariable("id") Long id, Model model){
        model.addAttribute("cl", colorService.detail(id));
        return "color/color-view-update";
    }

    @GetMapping("view-delete")
    public String viewDelete(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page){
        Page<Color> colorPage = colorService.pagingViewDelete(page, 5);
        model.addAttribute("colors", colorPage.getContent());
        model.addAttribute("pageProduct", colorPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "color/color-view-delete";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("cl") Color color, BindingResult result, Model model){
        if (result.hasErrors()){
            return "color/color-view-add";
        }else {
            for (Color cl : colorService.getAll()
            ) {
                if (color.getCode().equalsIgnoreCase(cl.getCode())) {
                    model.addAttribute("message", "Ma trung");
                    return "color/color-view-add";
                }
            }
        }
        colorService.add(color);
        return "redirect:/color/";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("cl") Color color, @PathVariable("id") Long id){
        color.setId(id);
        color.setDateCreate(colorService.detail(id).getDateCreate());
        colorService.update(color);
        return "redirect:/color/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Color color = colorService.detail(id);
        colorService.delete(color);
        return "redirect:/color/";
    }

    @GetMapping("/undo/{id}")
    public String undo(@PathVariable("id") Long id){
        Color color = colorService.detail(id);
        colorService.undo(color);
        return "redirect:/color/";
    }

    @GetMapping("search")
    public String search(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam("keyword") String keyword){
        Page<Color> colorPage = colorService.searchColor(keyword, page, 5);
        model.addAttribute("colors", colorPage.getContent());
        model.addAttribute("pageProduct", colorPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("keyword", keyword);
        return "color/color-search";
    }

    @PostMapping("upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Dữ liệu nằm trong sheet dầu tiên

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Bỏ qua header
                }

                Color color = new Color();
                color.setCode(row.getCell(0).getStringCellValue());
                color.setName(row.getCell(1).getStringCellValue());
                color.setDateCreate(LocalDate.now());
                color.setDateUpdate(LocalDate.now());
                color.setPersonCreate(row.getCell(2).getStringCellValue());
                color.setPersonUpdate(row.getCell(3).getStringCellValue());
                color.setStatus(0);
                colorService.add(color);
            }
            workbook.close();

            model.addAttribute("messageUpload", "Du lieu duoc them thanh cong");
            return "redirect:/color/";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Du lieu them khong thanh cong");
            return "redirect:/color/";
        }
    }

}
