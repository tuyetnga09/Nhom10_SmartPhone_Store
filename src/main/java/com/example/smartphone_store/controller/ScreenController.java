package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Screen;
import com.example.smartphone_store.service.ScreenService;
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
@RequestMapping("/screen/")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    // hien thi tat ca san phaam Screen
    @GetMapping("hien-thi")
    public String viewScreen(Model model, @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {
        Page<Screen> screen = screenService.viewShowActivityScreen(0, pageNo, 5);
        model.addAttribute("screens", screen.getContent());
        model.addAttribute("screen", screen);
        model.addAttribute("screenTotalPages", screen.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "screen/view-screen";
    }

    // hien thi trang add screen
    @GetMapping("view-add")
    public String viewAddScreen(Model model) {
        model.addAttribute("screen", new Screen());
        return "screen/add";
    }

    // hien thi trang add screen
    @GetMapping("view-update/{id}")
    public String viewUpdateScreen(Model model, @PathVariable("id")Long id) {
        Screen screen = screenService.findById(id);
        model.addAttribute("screen", screen);
        return "screen/view-update";
    }

    // hien thi detail screen
    @GetMapping("detail/{id}")
    public String viewDetailScreen(Model model, @PathVariable("id")Long id) {
        Screen screen = screenService.findById(id);
        model.addAttribute("screen", screen);
        return "screen/detail";
    }

    // xoa sp
    @GetMapping("remove/{id}")
    public String removeScreen(Model model, @PathVariable("id")Long id) {
        screenService.deleteActivitytatus(id);
        return "redirect:/screen/hien-thi";
    }

    // add screen
    @PostMapping("add")
    public String add(Model model, @Valid @ModelAttribute("screen") Screen screen,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("screen", screen);
            return "screen/add";
        }
        screenService.save(screen);
        return "redirect:/screen/hien-thi";
    }

    // update screen
    @PostMapping("update")
    public String update(Model model, @Valid @ModelAttribute("screen") Screen screen,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("screen", screen);
            return "screen/view-update";
        }
        screenService.update(screen);
        return "redirect:/screen/hien-thi";
    }

    // xoa sp screen
    @GetMapping("seach")
    public String seachScreen(Model model, @RequestParam("seach")String seach,
                           @RequestParam(name = "pageNo", defaultValue = "0", required = false)Integer pageNo) {
        if (seach.trim().isEmpty()){
            return  "redirect:/screen/hien-thi";
        }
        Page<Screen> screen = screenService.viewSeachAllScreen(seach, pageNo, 5);
        model.addAttribute("screens", screen.getContent());
        model.addAttribute("screen", screen);
        model.addAttribute("screenTotalPages", screen.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("seachUri", seach);

        return "screen/view-seach";
    }
}
