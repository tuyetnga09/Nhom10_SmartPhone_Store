package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Ram;
import com.example.smartphone_store.service.RamService;
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

import java.util.List;

@Controller
@RequestMapping("/ram/")
public class RamController {

    @Autowired
    private RamService ramService;

    // hien thi tat ca san phaam ram
    @GetMapping("hien-thi")
    public String viewRam(Model model, @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {
        Page<Ram> ram = ramService.viewShowActivityRam(0, pageNo, 5);
        model.addAttribute("rams", ram.getContent());
        model.addAttribute("ram", ram);
        model.addAttribute("ramTotalPages", ram.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "ram/view-ram";
    }

    // hien thi trang ram delete
    @GetMapping("view-delete")
    public String viewRamDelete(Model model, @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {
        Page<Ram> ram = ramService.viewShowActivityRam(1, pageNo, 5);
        model.addAttribute("rams", ram.getContent());
        model.addAttribute("ram", ram);
        model.addAttribute("ramTotalPages", ram.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "ram/view-delete";
    }

    // hien thi trang add ram
    @GetMapping("view-add")
    public String viewAddRam(Model model) {
        model.addAttribute("ram", new Ram());
        return "ram/add";
    }

    // hien thi trang update ram
    @GetMapping("view-update/{id}")
    public String viewUpdateRam(Model model, @PathVariable("id") Long id) {
        Ram ram = ramService.findById(id);
        model.addAttribute("ram", ram);
        return "ram/view-update";
    }

    // hien thi detail ram
    @GetMapping("detail/{id}")
    public String viewDetailRam(Model model, @PathVariable("id") Long id) {
        Ram ram = ramService.findById(id);
        model.addAttribute("ram", ram);
        return "ram/detail";
    }

    // xoa sp
    @GetMapping("remove/{id}")
    public String removeRam(Model model, @PathVariable("id") Long id) {
        ramService.deleteActivitytatus(id);
        return "redirect:/ram/hien-thi";
    }

    // add ram
    @PostMapping("add")
    public String add(Model model, @Valid @ModelAttribute("ram") Ram ram,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ram", ram);
            return "ram/add";
        } else {
            List<Ram> rams = ramService.getAll();
            for (Ram ramCheckMa : rams) {
                if (ram.getCode().equalsIgnoreCase(ramCheckMa.getCode())) {
                    model.addAttribute("message", "* Mã đã tôn tại!");
                    model.addAttribute("ram", ram);
                    return "ram/add";
                }
            }
        }
        ramService.save(ram);
        return "redirect:/ram/hien-thi";
    }

    // update ram
    @PostMapping("update")
    public String update(Model model, @Valid @ModelAttribute("ram") Ram ram,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ram", ram);
            return "ram/view-update";
        }
        ramService.update(ram);
        return "redirect:/ram/hien-thi";
    }

    // xoa sp
    @GetMapping("seach")
    public String seachRam(Model model, @RequestParam("seach") String seach,
                           @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {
        if (seach.trim().isEmpty()) {
            return "redirect:/ram/hien-thi";
        }
        Page<Ram> ram = ramService.viewSeachAllRam(seach, pageNo, 5);
        model.addAttribute("rams", ram.getContent());
        model.addAttribute("ram", ram);
        model.addAttribute("ramTotalPages", ram.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("seachUri", seach);

        return "ram/view-seach";
    }

    // khôi phục dữ liệu sp
    @GetMapping("return/{id}")
    public String returnRam(Model model, @PathVariable("id") Long id) {
        ramService.returnActivitytatus(id);
        return "redirect:/ram/hien-thi";
    }
}
