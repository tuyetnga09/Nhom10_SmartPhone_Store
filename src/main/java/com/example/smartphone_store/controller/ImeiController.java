package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Imei;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.service.ImeiService;
import com.example.smartphone_store.service.ProductDetailService;
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
@RequestMapping("/imei/")
public class ImeiController {
    @Autowired
    private ImeiService imeiService;

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("display")
    public String getAllImei(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<Imei> imeis = imeiService.getPage(pageNo, 5);
        model.addAttribute("imeis", imeis.getContent());
        model.addAttribute("imeiPages", imeis.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("imei", new Imei());
        model.addAttribute("productDetail", productDetailService.getAll());
        return "imei/imei";
    }

    @GetMapping("view-add")
    public String viewAddProductDetail(Model model) {
        model.addAttribute("imei", new Imei()); // Thay đổi kiểu dữ liệu thành Imei
        model.addAttribute("productDetail", new ProductDetail());
        model.addAttribute("productDetailList", productDetailService.getAll());
        return "imei/view-add";
    }

    @PostMapping("add")
    public String addProductDetail(Model model, @Valid @ModelAttribute("imei") Imei imei, BindingResult result) {
        if (result.hasErrors()) {
            return "imei/view-add";
        } else {
            for (Imei imei1 : imeiService.getAll()) {
                if (imei.getCode().equals(imei1.getCode())) {
                    model.addAttribute("message", "(*) Mã đang trùng");
                    return "imei/view-add";
                }
            }
        }
        imeiService.addImei(imei);
        return "redirect:/imei/display";
    }


}
