package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.Imei;
import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.service.BatteryService;
import com.example.smartphone_store.service.CapacityService;
import com.example.smartphone_store.service.CategoryService;
import com.example.smartphone_store.service.ChipService;
import com.example.smartphone_store.service.ColorService;
import com.example.smartphone_store.service.ImeiService;
import com.example.smartphone_store.service.ManufactureService;
import com.example.smartphone_store.service.ProductDetailService;
import com.example.smartphone_store.service.RamService;
import com.example.smartphone_store.service.ScreenService;
import com.example.smartphone_store.service.impl.ProductService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productDetails/")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CapacityService capacityService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ManufactureService manufactureService;
    @Autowired
    private BatteryService batteryService;
    @Autowired
    private ChipService chipService;
    @Autowired
    private RamService ramService;
    @Autowired
    private ScreenService screenService;

    @Autowired
    private ImeiService imeiService;

    @GetMapping("display")
    public String getAllProductDetails(Model model, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<ProductDetail> productDetails = productDetailService.getPage(pageNo, 5);
        model.addAttribute("productDetails", productDetails.getContent());
        model.addAttribute("productDetailPages", productDetails.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        model.addAttribute("proDetail", new ProductDetail());
        model.addAttribute("capacities", capacityService.getAll());
        model.addAttribute("color", colorService.getAll());
        model.addAttribute("manufacturer", manufactureService.getAll());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("battery", batteryService.getAll());
        model.addAttribute("chip", chipService.getAll());
        model.addAttribute("ram", ramService.getAll());
        model.addAttribute("screen", screenService.getAll());
        model.addAttribute("product", productService.getAll());
        return "/productDetail/productDetails";
    }

    @GetMapping("view-add")
    public String viewAddProductDetail(Model model) {
        model.addAttribute("proDetail", new ProductDetail());
        model.addAttribute("capacities", capacityService.getAll());
        model.addAttribute("color", colorService.getAll());
        model.addAttribute("manufacturer", manufactureService.getAll());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("battery", batteryService.getAll());
        model.addAttribute("chip", chipService.getAll());
        model.addAttribute("ram", ramService.getAll());
        model.addAttribute("screen", screenService.getAll());
        model.addAttribute("product", productService.getAll());
        return "/productDetail/productDetail-view-add";
    }
    @PostMapping("add")
    public String addProductDetail(Model model, @ModelAttribute("proDetail") ProductDetail productDetail,
                                   @RequestParam(value = "imeiFile", required = false) MultipartFile imeiFile) {
        try {
            // Thêm chi tiết sản phẩm vào cơ sở dữ liệu
            productDetailService.addProductDetail(productDetail);

            // Đọc file Excel và lấy danh sách IMEI từ file (nếu có)
            List<String> imeis = new ArrayList<>();
            int quantity = 1; // Số lượng mặc định

            if (imeiFile != null && !imeiFile.isEmpty()) {
                imeis = ExcelUtil.extractImeisFromExcel(imeiFile);
                quantity = imeis.size();
            }

            // Tạo danh sách IMEI và gắn cho chi tiết sản phẩm
            for (String imei : imeis) {
                Imei newImei = new Imei();
                newImei.setCode(imei);
                newImei.setProductDetail(productDetail);
                imeiService.addImei(newImei);
            }

            // Cập nhật số lượng sản phẩm
            Product product = productDetail.getProduct();
            product.setQuantity(product.getQuantity() + quantity);
            productService.update(product);

            return "redirect:/productDetails/display";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi đọc file Excel.");
            return "/productDetail/productDetail-view-add";
        }
    }

    @GetMapping("remove/{id}")
    public String removeProductDetail(@PathVariable("id") Long id) {
        ProductDetail productDetail = productDetailService.getOne(id);
        productDetailService.removeProductDetail(productDetail);
        return "redirect:/productDetails/display";
    }

    @GetMapping("view-update/{id}")
    public String viewUpdateProductDetail(Model model, @PathVariable("id") Long id) {
        ProductDetail productDetail = productDetailService.getOne(id);
        model.addAttribute("proDetail", productDetail);
        model.addAttribute("capacities", capacityService.getAll());
        model.addAttribute("color", colorService.getAll());
        model.addAttribute("manufacturer", manufactureService.getAll());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("battery", batteryService.getAll());
        model.addAttribute("chip", chipService.getAll());
        model.addAttribute("ram", ramService.getAll());
        model.addAttribute("screen", screenService.getAll());
        model.addAttribute("product", productService.getAll());
        return "/productDetail/view-update";
    }

    @PostMapping("update")
    public String updateProductDetail(Model model, @Valid @ModelAttribute("proDetail") ProductDetail productDetail,
                                      BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("proDetail", productDetail);
            model.addAttribute("capacities", capacityService.getAll());
            model.addAttribute("color", colorService.getAll());
            model.addAttribute("manufacturer", manufactureService.getAll());
            model.addAttribute("category", categoryService.getAll());
            model.addAttribute("battery", batteryService.getAll());
            model.addAttribute("chip", chipService.getAll());
            model.addAttribute("ram", ramService.getAll());
            model.addAttribute("screen", screenService.getAll());
            model.addAttribute("product", productService.getAll());
            System.out.println("hi pro =====>");
            System.out.println(productDetail.toString());
            return "productDetail/view-update";
        }
        productDetailService.updateProduct(productDetail);
        return "redirect:/productDetails/display";
    }

    @GetMapping("search")
    public String searchProductDetail(Model model,
                                      @RequestParam(value = "search", required = false) String search,
                                      @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
        Page<ProductDetail> productDetails = productDetailService.viewSeachAllProductDetail(search, pageNo, 5);
        model.addAttribute("productDetails", productDetails.getContent());
        model.addAttribute("productDetailPages", productDetails.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "/productDetail/proDetail_viewSearch";
    }

    @GetMapping("return-delete")
    public String viewDelete(Model model, @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo) {
        Page<ProductDetail> productDetails = productDetailService.getReturnDelete(pageNo, 5);
        model.addAttribute("productDetails", productDetails.getContent());
        model.addAttribute("productDetailPages", productDetails.getTotalPages());
        model.addAttribute("pageNumber", pageNo);
        return "productDetail/view-delete-productdetail";
    }

    // khoi phuc du lieu
    @GetMapping("return-productdetail/{id}")
    public String returnProductDetail(Model model, @PathVariable("id") Long id) {
        productDetailService.returnProductDetail(id);
        return "redirect:/productDetails/display";
    }

}
