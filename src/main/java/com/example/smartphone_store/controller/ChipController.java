package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.Chip;
import com.example.smartphone_store.service.BatteryService;
import com.example.smartphone_store.service.ChipService;
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
@RequestMapping("/chip/")
public class ChipController {
    @Autowired
    private ChipService chipService;

    @GetMapping("display")
    public String displayChip(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Chip> chipList = chipService.getPage(pageNo, 5);
        model.addAttribute("chipList", chipList.getContent());
        model.addAttribute("chipPages", chipList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("chip", new Chip());
        return "/chip/chip";
    }

    @GetMapping("view-add")
    public String viewAddChip(Model model) {
        model.addAttribute("chip", new Battery());
        return "/chip/chip-view-add";
    }

    @PostMapping("add")
    public String addChip(Model model, @Valid @ModelAttribute("chip") Chip chip, BindingResult result) {
        if (result.hasErrors()) {
            return "chip/chip-view-add";
        } else {
            for (Chip chip1 : chipService.getAll()) {
                if (chip.getCode().equals(chip1.getCode())) {
                    model.addAttribute("message", "(*) Mã đang trùng");
                    return "chip/chip-view-add";
                }
            }
        }
        chipService.addChip(chip);
        return "redirect:/chip/display";
    }


    @GetMapping("remove/{id}")
    public String removeChip(@PathVariable("id") Long id) {
        Chip chip = chipService.getOne(id);
        chipService.removeChip(chip);
        return "redirect:/chip/display";
    }

    @GetMapping("return-delete")
    public String returnDeleteChip(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Chip> chipList = chipService.getReturnDelete(pageNo, 5);
        model.addAttribute("chipList", chipList.getContent());
        model.addAttribute("chipPages", chipList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("chip", new Chip());
        return "/chip/chip_delete";
    }

    @GetMapping("return/{id}")
    public String returnChip(@PathVariable("id") Long id) {
        Chip chip = chipService.getOne(id);
        chipService.returnChip(chip);
        return "redirect:/chip/return-delete";
    }

    @GetMapping("detail/{id}")
    public String detailChip(Model model, @PathVariable("id") Long id) {
        Chip chip = chipService.getOne(id);
        model.addAttribute("chip", chip);
        model.addAttribute("chipList", chipService.getAll());
        return "/chip/chip_detail";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdateChip(Model model, @PathVariable("id") Long id) {
        Chip chip = chipService.getOne(id);
        model.addAttribute("chip", chip);
        return "/chip/chip_viewUpdate";
    }

    @PostMapping("update")
    public String updateChip(@Valid @ModelAttribute("chip") Chip chip, BindingResult result) {
        if (result.hasErrors()) {
            return "/chip/chip_viewUpdate";
        }
        chipService.updateChip(chip);
        return "redirect:/chip/display";
    }

    @GetMapping("search")
    public String searchChip(Model model,
                             @RequestParam(value = "search", required = false) String search,
                             @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Chip> chipList = chipService.viewSearchAllBattery(search, pageNo, 5);
        model.addAttribute("chipList", chipList.getContent());
        model.addAttribute("chipPages", chipList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "/chip/chip_viewSearch";
    }

    @PostMapping("upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Dữ liệu nằm trong sheet đầu tiên

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Bỏ qua header
                }

                String code = row.getCell(0).getStringCellValue();
                Chip chip = chipService.findByCode(code);

                if (chip != null) {
                    // Battery đã tồn tại, cập nhật thông tin
                    chip.setName(row.getCell(1).getStringCellValue());
                    chip.setDateUpdate(LocalDate.now());
                    chip.setPersonUpdate(row.getCell(3).getStringCellValue());
                    chipService.updateChip(chip);
                } else {
                    // Battery chưa tồn tại, thêm mới
                    Chip newChip = new Chip();
                    newChip.setCode(code);
                    newChip.setName(row.getCell(1).getStringCellValue());
                    newChip.setDateCreate(LocalDate.now());
                    newChip.setDateUpdate(LocalDate.now());
                    newChip.setPersonCreate(row.getCell(2).getStringCellValue());
                    newChip.setPersonUpdate(row.getCell(3).getStringCellValue());
                    newChip.setStatus(0);
                    chipService.addChip(newChip);
                }
            }

            workbook.close();

            model.addAttribute("messageUpload", "Dữ liệu được thêm thành công");
            return "redirect:/chip/display";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Dữ liệu thêm không thành công");
            return "redirect:/chip/display";
        }
    }
}
