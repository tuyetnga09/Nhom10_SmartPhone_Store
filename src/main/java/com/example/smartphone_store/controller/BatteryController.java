package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.service.BatteryService;
import com.example.smartphone_store.service.impl.BatterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
