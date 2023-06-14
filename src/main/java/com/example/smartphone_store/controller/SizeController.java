package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Screen;
import com.example.smartphone_store.entity.Size;
import com.example.smartphone_store.service.SizeService;
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
@RequestMapping("/size/")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    // hien thi tat ca san phaam size
    @GetMapping("hien-thi")
    public String viewSize(Model model, @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {
        Page<Size> size = sizeService.viewShowActivitySize(0, pageNo, 5);
        model.addAttribute("sizes", size.getContent());
        model.addAttribute("size", size);
        model.addAttribute("sizeTotalPages", size.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "size/view-size";
    }

    // hien thi tat ca san phaam size đã xoá
    @GetMapping("view-delete")
    public String viewSizeDelete(Model model, @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {
        Page<Size> size = sizeService.viewShowActivitySize(1, pageNo, 5);
        model.addAttribute("sizes", size.getContent());
        model.addAttribute("size", size);
        model.addAttribute("sizeTotalPages", size.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "size/view-delete";
    }

    // hien thi trang add size
    @GetMapping("view-add")
    public String viewAddSize(Model model) {
        model.addAttribute("size", new Size());
        return "size/add";
    }

    // hien thi trang add size
    @GetMapping("view-update/{id}")
    public String viewUpdateSize(Model model, @PathVariable("id")Long id) {
        Size size = sizeService.findById(id);
        model.addAttribute("size", size);
        return "size/view-update";
    }

    // hien thi detail size
    @GetMapping("detail/{id}")
    public String viewDetailSize(Model model, @PathVariable("id")Long id) {
        Size size = sizeService.findById(id);
        model.addAttribute("size", size);
        return "size/detail";
    }

    // xoa sp size
    @GetMapping("remove/{id}")
    public String removeSize(Model model, @PathVariable("id")Long id) {
        sizeService.deleteActivitytatus(id);
        return "redirect:/size/hien-thi";
    }

    // khôi phục sp size
    @GetMapping("return/{id}")
    public String returnSize(Model model, @PathVariable("id")Long id) {
        sizeService.returnActivitytatus(id);
        return "redirect:/size/hien-thi";
    }

    // add size
    @PostMapping("add")
    public String add(Model model, @Valid @ModelAttribute("size") Size size,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("size", size);
            return "size/add";
        }else {
            List<Size> sizes = sizeService.getAll();
            for (Size sizeCheckMa: sizes) {
                if (size.getCode().equalsIgnoreCase(sizeCheckMa.getCode())) {
                    model.addAttribute("message", "* Mã đã tôn tại!");
                    model.addAttribute("size", size);
                    return "size/add";
                }
            }
        }
        sizeService.save(size);
        return "redirect:/size/hien-thi";
    }

    // update size
    @PostMapping("update")
    public String update(Model model, @Valid @ModelAttribute("size") Size size,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("size", size);
            return "size/view-update";
        }
        sizeService.update(size);
        return "redirect:/size/hien-thi";
    }

    // xoa sp size
    @GetMapping("seach")
    public String seachSize(Model model, @RequestParam("seach")String seach,
                           @RequestParam(name = "pageNo", defaultValue = "0", required = false)Integer pageNo) {
        if (seach.trim().isEmpty()){
            return  "redirect:/size/hien-thi";
        }
        Page<Size> size = sizeService.viewSeachAllSize(seach, pageNo, 5);
        model.addAttribute("sizes", size.getContent());
        model.addAttribute("size", size);
        model.addAttribute("sizeTotalPages", size.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("seachUri", seach);

        return "size/view-seach";
    }
}
