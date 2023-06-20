package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.service.BatteryService;
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
@RequestMapping("/battery/")
public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    @GetMapping("display")
    public String displayBattery(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Battery> batteryList = batteryService.getPage(pageNo, 5);
        model.addAttribute("batteryList", batteryList.getContent());
        model.addAttribute("batteryPage", batteryList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("battry", new Battery());
        return "/battery/batteries";
    }

    @GetMapping("view-add")
    public String viewAddBattery(Model model) {
        model.addAttribute("battry", new Battery());
        return "/battery/battery-view-add";
    }

    @PostMapping("add")
    public String addBattery(Model model, @Valid @ModelAttribute("battry") Battery battery, BindingResult result) {
        if (result.hasErrors()) {
            return "battery/battery-view-add";
        } else {
            for (Battery bat : batteryService.getAll()) {
                if (battery.getCode().equals(bat.getCode())) {
                    model.addAttribute("message", "(*) Mã đang trùng");
                    return "battery/battery-view-add";
                }
            }
        }
        batteryService.addBattery(battery);
        return "redirect:/battery/display";
    }


    @GetMapping("remove/{id}")
    public String removeBattery(@PathVariable("id") Long id) {
        Battery battery = batteryService.getOne(id);
        batteryService.removeBattery(battery);
        return "redirect:/battery/display";
    }

    @GetMapping("return-delete")
    public String returnDeleteBattery(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Battery> batteryList = batteryService.getReturnDelete(pageNo, 5);
        model.addAttribute("batteryList", batteryList.getContent());
        model.addAttribute("batteryPage", batteryList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("battry", new Battery());
        return "/battery/batteries_delete";
    }

    @GetMapping("return/{id}")
    public String returnBattery(@PathVariable("id") Long id) {
        Battery battery = batteryService.getOne(id);
        batteryService.returnBattery(battery);
        return "redirect:/battery/return-delete";
    }

    @GetMapping("detail/{id}")
    public String detailBattery(Model model, @PathVariable("id") Long id) {
        Battery battery = batteryService.getOne(id);
        model.addAttribute("battry", battery);
        model.addAttribute("batteryList", batteryService.getAll());
        return "/battery/batteries_detail";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdateBattery(Model model, @PathVariable("id") Long id) {
        Battery battery = batteryService.getOne(id);
        model.addAttribute("battry", battery);
        return "/battery/capacities_viewUpdate";
    }

    @PostMapping("update")
    public String updateBattery(@Valid @ModelAttribute("battry") Battery battery, BindingResult result) {
        if (result.hasErrors()) {
            return "/battery/capacities_viewUpdate";
        }
        batteryService.updateBattery(battery);
        return "redirect:/battery/display";
    }

    @GetMapping("search")
    public String searchBattery(Model model,
                                @RequestParam(value = "search", required = false) String search,
                                @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Battery> batteryList = batteryService.viewSeachAllBattery(search, pageNo, 5);
        model.addAttribute("batteryList", batteryList.getContent());
        model.addAttribute("batteryPage", batteryList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "/battery/batteries_viewSearch";
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

                Battery battery = new Battery();
                battery.setCode(row.getCell(0).getStringCellValue());
                battery.setName(row.getCell(1).getStringCellValue());
                battery.setDateCreate(LocalDate.now());
                battery.setDateUpdate(LocalDate.now());
                battery.setPersonCreate(row.getCell(2).getStringCellValue());
                battery.setPersonUpdate(row.getCell(3).getStringCellValue());
                battery.setStatus(0);
                batteryService.addBattery(battery);
            }
            workbook.close();

            model.addAttribute("messageUpload", "Du lieu duoc them thanh cong");
            return "redirect:/battery/display";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Du lieu them khong thanh cong");
            return "redirect:/battery/display";
        }
    }


}
