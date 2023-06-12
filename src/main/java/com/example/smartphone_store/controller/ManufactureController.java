package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Manufacture;
import com.example.smartphone_store.service.ManufactureService;
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
@RequestMapping("/manufacture/")
public class ManufactureController {

    @Autowired
    private ManufactureService manufactureService;

    @GetMapping("")
    public String getAll(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Manufacture> manufacturePage = manufactureService.paging(page, 5);
        model.addAttribute("manufactures", manufacturePage.getContent());
        model.addAttribute("pageProduct", manufacturePage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("mn", new Manufacture());
        return "manufacture/manufacture";
    }

    @GetMapping("view-add")
    public String viewAdd(Model model) {
        model.addAttribute("mn", new Manufacture());
        return "manufacture/manufacture-view-add";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("mn", manufactureService.detail(id));
        return "manufacture/manufacture-view-update";
    }

    @GetMapping("view-delete")
    public String viewDelete(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Manufacture> manufacturePage = manufactureService.pagingViewDelete(page, 5);
        model.addAttribute("manufactures", manufacturePage.getContent());
        model.addAttribute("pageProduct", manufacturePage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "manufacture/manufacture-view-delete";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("mn") Manufacture manufacture, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "manufacture/manufacture-view-add";
        } else {
            for (Manufacture mn : manufactureService.getAll()
            ) {
                if (manufacture.getCode().equalsIgnoreCase(mn.getCode())) {
                    model.addAttribute("message", "Ma trung");
                    return "manufacture/manufacture-view-add";
                }
            }
        }
        manufactureService.add(manufacture);
        return "redirect:/manufacture/";
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("cl") Manufacture manufacture, @PathVariable("id") Long id) {
        manufacture.setId(id);
        manufacture.setDateCreate(manufactureService.detail(id).getDateCreate());
        manufactureService.update(manufacture);
        return "redirect:/manufacture/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Manufacture manufacture = manufactureService.detail(id);
        manufactureService.delete(manufacture);
        return "redirect:/manufacture/";
    }

    @GetMapping("undo/{id}")
    public String undo(@PathVariable("id") Long id) {
        Manufacture manufacture = manufactureService.detail(id);
        manufactureService.undo(manufacture);
        return "redirect:/manufacture/";
    }

    @GetMapping("search")
    public String search(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam("keyword") String keyword){
        Page<Manufacture> manufacturePage = manufactureService.searchManufacture(keyword, page, 5);
        model.addAttribute("manufactures", manufacturePage.getContent());
        model.addAttribute("pageProduct", manufacturePage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("keyword", keyword);
        return "manufacture/manufacture-search";
    }
}
