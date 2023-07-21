package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Images;
import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.service.IImages;
import com.example.smartphone_store.service.ProductDetailService;
import com.example.smartphone_store.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class WebController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private IImages imagesService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
        model.addAttribute("top10PD", listTop10);

        List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
        model.addAttribute("bestSelling", listBestSelling);

        List<ProductDetail> listLineProductDetail = productDetailService.getLineProductDetail();
        model.addAttribute("lineProductDetail", listLineProductDetail);

        return "pages/trang_chu";
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/bill")
    public String bill() {
        return "pages/BillPage";
    }

    @GetMapping("/single-product/{name}")
    public String singleProduct(Model model, @PathVariable("name") String name,
                                @RequestParam(name = "capacity", required = false, defaultValue = "") String capacityReques,
                                @RequestParam(name = "color", required = false, defaultValue = "") String colorReques,
                                @PathVariable(name = "capacity", required = false) String capacityPath,
                                @PathVariable(name = "color", required = false) String colorPath) {
//        System.out.println("====================================================================" +
//                "=================================================="+ nameproductPath + " "+capacityPath+" "+ capacityPath);

        String capacityPathOf = "";
        String colorPathOf = "";

        if (capacityReques != null) {
            capacityPathOf = capacityReques;
            model.addAttribute("capacityPathOf", capacityPathOf);
        }
        if (colorReques != null) {
            colorPathOf = colorReques;
            model.addAttribute("colorPathOf", colorPathOf);
        }

        if(!capacityReques.isEmpty() && !colorReques.isEmpty()){

            List<ProductDetail> productDetails =
                    productDetailService.findProductDetailByNameAndCapacityAndColor(name, colorReques, capacityReques);
            model.addAttribute("productDetails", productDetails);
            if (productDetails.isEmpty()){
                model.addAttribute("checkProductDetails", false);
            }else {
                model.addAttribute("checkProductDetails", true);
                List<String> nameImages = new ArrayList<>();
                productDetails.forEach(productDetail -> nameImages.add(productDetail.getImages()));
                model.addAttribute("nameImages", nameImages);
            }
//            System.out.println(capacityReques+" "+colorReques);
//            System.out.println("====================================================================" +
//                "==================================================" + productDetails.toString());

            List<String> nameCapacity = productDetailService.findNameCapacityByNameProductDetail(name);
            model.addAttribute("nameCapacity", nameCapacity); // true

            List<ProductDetail> productDetailsCapacityOrColor =
                    productDetailService.findProductDetailByNameAndCapacityOrColor(name, null, capacityReques);
            List<String> nameColors = new ArrayList<>();
            productDetailsCapacityOrColor.forEach(productDetail -> nameColors.add(productDetail.getColor().getName()));
            model.addAttribute("nameColor", nameColors);

            List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
            model.addAttribute("top10PD", listTop10);

            List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
            model.addAttribute("bestSelling", listBestSelling);

            return "pages/single_product";
        }else if (!capacityReques.isEmpty() || !colorReques.isEmpty()) {
            // khi capacityReques có gí trị thì chọn vào GB thì sẽ tự động chuyển các màu mà máy đó còn theo GB được chọn
            if (!capacityReques.isEmpty()) {
                model.addAttribute("checkProductDetails", true);
                List<ProductDetail> productDetails = productDetailService.findProductDetailByNameAndCapacityOrColor(name, null, capacityReques);
                model.addAttribute("productDetails", productDetails);

                List<String> nameCapacity = productDetailService.findNameCapacityByNameProductDetail(name);
                model.addAttribute("nameCapacity", nameCapacity); // true

//                List<String> nameColor = productDetailService.findNameColorByNameProductDetail(name);
                List<String> nameColors = new ArrayList<>();
                productDetails.forEach(productDetail -> nameColors.add(productDetail.getColor().getName()));
                model.addAttribute("nameColor", nameColors);

                List<String> nameImages = new ArrayList<>();
                productDetails.forEach(productDetail -> nameImages.add(productDetail.getImages()));
                model.addAttribute("nameImages", nameImages);

                List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
                model.addAttribute("top10PD", listTop10);

                List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
                model.addAttribute("bestSelling", listBestSelling);

                return "pages/single_product";

            } else {
                // khi colorReques có gí trị thì chọn vào màu sắc thì sẽ tự động chuyển các GB mà máy đó còn theo màu sắc được chọn

                model.addAttribute("checkProductDetails", true);
                List<ProductDetail> productDetails = productDetailService.findProductDetailByNameAndCapacityOrColor(name, colorReques, null);
                model.addAttribute("productDetails", productDetails);

                List<String> nameColor = productDetailService.findNameColorByNameProductDetail(name);
                model.addAttribute("nameColor", nameColor);

                List<String> nameCapacitys = new ArrayList<>();
                productDetails.forEach(productDetail -> nameCapacitys.add(productDetail.getCapacity().getName()));
                model.addAttribute("nameCapacity", nameCapacitys);

                List<String> nameImages = new ArrayList<>();
                productDetails.forEach(productDetail -> nameImages.add(productDetail.getImages()));
                model.addAttribute("nameImages", nameImages);

                List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
                model.addAttribute("top10PD", listTop10);

                List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
                model.addAttribute("bestSelling", listBestSelling);

                return "pages/single_product";
            }
        } else {
            model.addAttribute("checkProductDetails", true);
            model.addAttribute("name", name.trim());

            List<ProductDetail> productDetails = productDetailService.findProductDetailByNameAndStatus(name, 0);
            model.addAttribute("productDetails", productDetails); //có đổi

            List<String> nameCapacity = productDetailService.findNameCapacityByNameProductDetail(name);
            model.addAttribute("nameCapacity", nameCapacity);

            List<String> nameColor = productDetailService.findNameColorByNameProductDetail(name);
            model.addAttribute("nameColor", nameColor);

            List<String> nameImages = productDetailService.findImagesByNameProductDetail(name);
            model.addAttribute("nameImages", nameImages);// có đổi


            List<ProductDetail> listTop10 = productDetailService.getTop10NewProductDetail();
            model.addAttribute("top10PD", listTop10);

            List<ProductDetail> listBestSelling = productDetailService.getBestSelling();
            model.addAttribute("bestSelling", listBestSelling);

            return "pages/single_product";
        }
    }

    @GetMapping("/iphone-x")
    public String listProductIphoneX(Model model) {
        List<Product> list = productService.getProductIphoneX();
        model.addAttribute("list", list);
        return "pages/list_productdetail_findby_product";
    }

    @GetMapping("/iphone-11")
    public String listProductIphone11(Model model) {
        List<Product> list = productService.getProductIphone11();
        model.addAttribute("list", list);
        return "pages/list_productdetail_findby_product";
    }

    @GetMapping("/iphone-12")
    public String listProductIphone12(Model model) {
        List<Product> list = productService.getProductIphone12();
        model.addAttribute("list", list);
        return "pages/list_productdetail_findby_product";
    }

    @GetMapping("/iphone-13")
    public String listProductIphone13(Model model) {
        List<Product> list = productService.getProductIphone13();
        model.addAttribute("list", list);
        return "pages/list_productdetail_findby_product";
    }

    @GetMapping("/iphone-14")
    public String listProductIphone14(Model model) {
        List<Product> list = productService.getProductIphone14();
        model.addAttribute("list", list);
        return "pages/list_productdetail_findby_product";
    }

    @GetMapping("/productDetail/list")
    public String listProductDetail(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<Product> productPage = productService.selectByStatus(0, pageable);
        model.addAttribute("list", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail";
    }

    @GetMapping("/productDetail/list/bigger20000000")
    public String listProductDetailBigger20000000(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Product> productPage = productService.getProductByPriceBigger20000000(page, 9);
        model.addAttribute("list", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail_bigger20000000";
    }

    @GetMapping("/productDetail/list/less10000000")
    public String listProductDetailLess10000000(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Product> productPage = productService.getProductByPriceLess10000000(page, 9);
        model.addAttribute("list", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail_less10000000";
    }

    @GetMapping("/productDetail/list/from10000000to20000000")
    public String listProductDetailFrom10000000to20000000(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Product> productPage = productService.getProductByPriceFrom10000000To20000000(page, 9);
        model.addAttribute("list", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        return "pages/list_productdetail_from10000000to20000000";
    }

    @GetMapping("/bill-status")
    public String billStatus() {
        return "pages/bill_status";
    }
}
