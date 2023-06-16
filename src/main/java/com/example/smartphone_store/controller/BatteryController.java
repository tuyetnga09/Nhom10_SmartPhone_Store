package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.service.BatteryService;
import com.example.smartphone_store.service.impl.BatterServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
