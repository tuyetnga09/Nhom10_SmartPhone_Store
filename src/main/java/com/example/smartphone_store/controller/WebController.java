package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.service.ProductDetailService;
import com.example.smartphone_store.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class WebController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(Model model){
        List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
        model.addAttribute("top10PD", listTop10);

        List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
        model.addAttribute("bestSelling", listBestSelling);

        List<ProductDetail> listLineProductDetail = productDetailService.getLineProductDetail();
        model.addAttribute("lineProductDetail", listLineProductDetail);

        return "pages/trang_chu";
    }

    @GetMapping("/login")
    public String login(){
        return "pages/login";
    }

    @GetMapping("/bill")
    public String bill(){
        return "pages/BillPage";
    }

    @GetMapping("/single-product")
    public String singleProduct(Model model){
        List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
        model.addAttribute("top10PD", listTop10);

        List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
        model.addAttribute("bestSelling", listBestSelling);

        return "pages/single_product";
    }

    @GetMapping("/product/{id}")
    public String listProduct(Model model, @PathVariable("id") Long id, @RequestParam(value = "page", defaultValue = "0") Integer page){
        List<Product> productList = productService.findByStatus(0);
        model.addAttribute("productList", productList);
        Page<ProductDetail> productDetailPage = productDetailService.findProductDetailByStatusAndProductId(0, id, page, 9);
        model.addAttribute("list", productDetailPage);
        model.addAttribute("totalPages", productDetailPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("id", id);

        return "pages/list_productdetail_findby_product";
    }

    @GetMapping("/productDetail/list")
    public String listProductDetail(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page){
        List<Product> productList = productService.findByStatus(0);
        model.addAttribute("productList", productList);

        Page<ProductDetail> productDetailPage = productDetailService.getPage(page, 9);
        model.addAttribute("list", productDetailPage.getContent());
        model.addAttribute("totalPages", productDetailPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail";
    }

    @GetMapping("/productDetail/list/bigger20000000")
    public String listProductDetailBigger20000000(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page){
        List<Product> productList = productService.findByStatus(0);
        model.addAttribute("productList", productList);

        Page<ProductDetail> productDetailPage = productDetailService.getProductDetailByPriceBigger20000000(page, 9);
        model.addAttribute("list", productDetailPage.getContent());
        model.addAttribute("totalPages", productDetailPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail_bigger20000000";
    }

    @GetMapping("/productDetail/list/less10000000")
    public String listProductDetailLess10000000(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page){
        List<Product> productList = productService.findByStatus(0);
        model.addAttribute("productList", productList);

        Page<ProductDetail> productDetailPage = productDetailService.getProductDetailByPriceLess10000000(page, 9);
        model.addAttribute("list", productDetailPage.getContent());
        model.addAttribute("totalPages", productDetailPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail_less10000000";
    }

    @GetMapping("/productDetail/list/from10000000to20000000")
    public String listProductDetailFrom10000000to20000000(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page){
        List<Product> productList = productService.findByStatus(0);
        model.addAttribute("productList", productList);

        Page<ProductDetail> productDetailPage = productDetailService.getProductDetailByPriceFrom10000000To20000000(page, 9);
        model.addAttribute("list", productDetailPage.getContent());
        model.addAttribute("totalPages", productDetailPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail_from10000000to20000000";
    }

    @GetMapping("/bill-status")
    public String billStatus(){
        return "pages/bill_status";
    }
}
