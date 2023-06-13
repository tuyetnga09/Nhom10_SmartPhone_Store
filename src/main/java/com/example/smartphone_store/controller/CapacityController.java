package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.service.CapacityService;
import jakarta.validation.Valid;
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

@Controller
@RequestMapping("/capacity/")
public class CapacityController {
    @Autowired
    private CapacityService capacityService;

    @GetMapping("display")
    public String displayCapacity(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Capacity> capacityList = capacityService.getPage(pageNo, 5);
        model.addAttribute("capacityList", capacityList.getContent());
        model.addAttribute("capacityPage", capacityList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("Capa", new Capacity());
        return "/capacity/capacities";
    }


    @GetMapping("view-add")
    public String viewAddCapacity(Model model) {
        model.addAttribute("Capa", new Capacity());
        return "/capacity/capacities_viewAdd";
    }

    @PostMapping("add")
    public String addCapacity(Model model,@Valid @ModelAttribute("Capa") Capacity capacity, BindingResult result) {
        if (result.hasErrors()) {
            return "/capacity/capacities_viewAdd";
        }else{
            for(Capacity cap : capacityService.getAll()){
                if(capacity.getCode().equals(cap.getCode())){
                    model.addAttribute("messege", "(*) Ma dang trung");
                    return "/capacity/capacities_viewAdd";
                }
            }
        }
        capacityService.addCapacity(capacity);
        return "redirect:/capacity/display";
    }

    @GetMapping("remove/{id}")
    public String removeCapacity(@PathVariable("id") Integer id) {
        Capacity capacity = capacityService.getOne(id);
        capacityService.removeCapacity(capacity);
        return "redirect:/capacity/display";
    }

    @GetMapping("return-delete")
    public String returnDeleteCapacity(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Capacity> capacityList = capacityService.getReturnDelete(pageNo, 5);
        model.addAttribute("capacityList", capacityList.getContent());
        model.addAttribute("capacityPage", capacityList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("Capa", new Capacity());
        return "/capacity/capacities_delete";
    }

    @GetMapping("return/{id}")
    public String returnCapacity(@PathVariable("id") Integer id) {
        Capacity capacity = capacityService.getOne(id);
        capacityService.returnCapacity(capacity);
        return "redirect:/capacity/return-delete";
    }

    @GetMapping("detail/{id}")
    public String detailCapacity(Model model, @PathVariable("id") Integer id) {
        Capacity capacity = capacityService.getOne(id);
        model.addAttribute("Capa", capacity);
        model.addAttribute("capacityList", capacityService.getAll());
        return "/capacity/capacities_detail";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdateCapacity(Model model, @PathVariable("id") Integer id) {
        Capacity capacity = capacityService.getOne(id);
        model.addAttribute("Capa", capacity);
        return "/capacity/capacities_viewUpdate";
    }

    @PostMapping("update")
    public String updateCapacity(@Valid @ModelAttribute("Capa") Capacity capacity, BindingResult result) {
        if (result.hasErrors()) {
            return "/capacity/capacities_viewUpdate";
        }
        capacityService.updateCapacity(capacity);
        return "redirect:/capacity/display";
    }

    @GetMapping("search")
    public String searchCapacity(Model model,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Capacity> capacityList = capacityService.searchCapacity(search, pageNo, 5);
        model.addAttribute("capacityList", capacityList.getContent());
        model.addAttribute("capacityPage", capacityList.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "/capacity/capacities_viewSearch";
    }
}
