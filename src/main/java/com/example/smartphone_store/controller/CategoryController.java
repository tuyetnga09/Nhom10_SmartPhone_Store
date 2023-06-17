package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.Category;
import com.example.smartphone_store.service.CategoryService;
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
import java.util.List;

@Controller
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("display")
    public String displayCategory(Model model, @RequestParam(value = "pageNo", defaultValue = "0")Integer pageNo){
        Page<Category> categoryList = categoryService.getPage(pageNo, 5);
        model.addAttribute("categoryList", categoryList.getContent());
        model.addAttribute("categoryPage", categoryList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("Cate", new Category());
        return "/category/categories";
    }

    @GetMapping("view-add")
    public String viewAddCategory(Model model){
        model.addAttribute("Cate", new Category());
        return "/category/categories_viewAdd";
    }

    @PostMapping("add")
    public String addCategory(Model model,@Valid @ModelAttribute("Cate")Category category, BindingResult result){
        if(result.hasErrors()){
            return "/category/categories_viewAdd";
        }else{
            for(Category cate : categoryService.getAll()){
                if(category.getCode().equals(cate.getCode())){
                    model.addAttribute("messege", "(*) Ma dang trung");
                    return "/category/categories_viewAdd";
                }
            }
        }
        categoryService.addCategory(category);
        return "redirect:/category/display";
    }

    @GetMapping("return-delete")
    public String returnDeleteCategory(Model model, @RequestParam(value = "pageNo", defaultValue = "0")Integer pageNo){
        Page<Category> categoryList = categoryService.getReturnDelete(pageNo, 5);
        model.addAttribute("categoryList", categoryList.getContent());
        model.addAttribute("categoryPage", categoryList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("Cate", new Category());
        return "/category/categories_delete";
    }

    @GetMapping("remove/{id}")
    public String removeCategory(@PathVariable("id")Integer id){
//        Category category = categoryService.getOne(id);
//        categoryService.removeCategory(category);
        categoryService.delete(id);
        return "redirect:/category/display";
    }

    @GetMapping("return/{id}")
    public String returnCategory(@PathVariable("id")Integer id){
        Category category = categoryService.getOne(id);
        categoryService.returnCapacity(category);
        return "redirect:/category/return-delete";
    }

    @GetMapping("detail/{id}")
    public String detailCategory(Model model, @PathVariable("id")Integer id){
        Category category = categoryService.getOne(id);
        model.addAttribute("Cate", category);
        return "/category/categories_detail";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdateCategory(Model model, @PathVariable("id")Integer id){
        Category category = categoryService.getOne(id);
        model.addAttribute("Cate", category);
        return "/category/categories_viewUpdate";
    }

    @PostMapping("update")
    public String updateCategory(@Valid @ModelAttribute("Cate")Category category, BindingResult result){
        if(result.hasErrors()){
            return "/category/categories_viewUpdate";
        }
        categoryService.updateCategory(category);
        return "redirect:/category/display";
    }

    @GetMapping("search")
    public String searchCapacity(Model model,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Category> categories = categoryService.searchCategory(search, pageNo, 5);
        model.addAttribute("categoryList", categories.getContent());
        model.addAttribute("categoryPage", categories.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "/category/categories_viewSearch";
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

                Category category = new Category();
                category.setCode(row.getCell(0).getStringCellValue());
                category.setName(row.getCell(1).getStringCellValue());
                category.setDateCreate(LocalDate.now());
                category.setDateUpdate(LocalDate.now());
                category.setPersonCreate(row.getCell(2).getStringCellValue());
                category.setPersonUpdate(row.getCell(3).getStringCellValue());
                category.setStatus(0);

//                if (capacityRepository.existsByCode(capacity.getCode())) {
//                    model.addAttribute("error", "Duplicate code: " + capacity.getCode());
//                    return "error";
//                }

                categoryService.addCategory(category);
            }
            workbook.close();

            model.addAttribute("messageUpload", "Du lieu duoc them thanh cong");
            return "redirect:/category/display";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Du lieu them khong thanh cong");
            return "redirect:/category/display";
//            return "/capacity/capacities";
        }
    }

}
