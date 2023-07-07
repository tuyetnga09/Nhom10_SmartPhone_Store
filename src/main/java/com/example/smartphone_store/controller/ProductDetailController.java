package com.example.smartphone_store.controller;

import com.example.smartphone_store.DAO.ImagesDAO;
import com.example.smartphone_store.DAO.ProductDetailDAO;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

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
    public String addProductDetail(Model model, @Valid @ModelAttribute("proDetail") ProductDetailDAO productDetailDAO,
                                   BindingResult bindingResult,
                                   @RequestParam(value = "imeiFile", required = false) MultipartFile imeiFile) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("proDetail", productDetailDAO);
//            model.addAttribute("capacities", capacityService.getAll());
//            model.addAttribute("color", colorService.getAll());
//            model.addAttribute("manufacturer", manufactureService.getAll());
//            model.addAttribute("category", categoryService.getAll());
//            model.addAttribute("battery", batteryService.getAll());
//            model.addAttribute("chip", chipService.getAll());
//            model.addAttribute("ram", ramService.getAll());
//            model.addAttribute("screen", screenService.getAll());
//            model.addAttribute("product", productService.getAll());
//            return "productDetail/productDetail-view-add";
//        }
//        try {
//
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productDetailDAO.getImages().getOriginalFilename());
//            Files.write(fileNameAndPath, productDetailDAO.getImages().getBytes());
//
//
//            ProductDetail productDetail = new ProductDetail();
//            productDetail.setCode(productDetailDAO.getCode());
//            productDetail.setName(productDetailDAO.getName());
//            productDetail.setCapacity(productDetailDAO.getCapacity());
//            productDetail.setColor(productDetailDAO.getColor());
//            productDetail.setManufacture(productDetailDAO.getManufacture());
//            productDetail.setCategory(productDetailDAO.getCategory());
//            productDetail.setProduct(productDetailDAO.getProduct());
//            productDetail.setChip(productDetailDAO.getChip());
//            productDetail.setScreen(productDetailDAO.getScreen());
//            productDetail.setRam(productDetailDAO.getRam());
//            productDetail.setPersonCreate(productDetailDAO.getPersonCreate());
//            productDetail.setPrice(productDetailDAO.getPrice());
//            productDetail.setImages("/images/" + productDetailDAO.getImages().getOriginalFilename());
//
//
//            // Thêm chi tiết sản phẩm vào cơ sở dữ liệu
//            productDetailService.addProductDetail(productDetail);
//
//            // Đọc file Excel và lấy danh sách IMEI từ file (nếu có)
//            List<String> imeis = new ArrayList<>();
//            int quantity = 1; // Số lượng mặc định
//
//            if (imeiFile != null && !imeiFile.isEmpty()) {
//                imeis = ExcelUtil.extractImeisFromExcel(imeiFile);
//                quantity = imeis.size();
//            }
//
//            // Tạo danh sách IMEI và gắn cho chi tiết sản phẩm
//            for (String imei : imeis) {
//                Imei newImei = new Imei();
//                newImei.setCode(imei);
//                newImei.setProductDetail(productDetail);
//                imeiService.addImei(newImei);
//            }
//
//            // Cập nhật số lượng sản phẩm
//            Product product = productDetail.getProduct();
//            product.setQuantity(product.getQuantity() + quantity);
//            productService.update(product);
//
//            return "redirect:/productDetails/display";
//        } catch (IOException e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Lỗi khi đọc file Excel.");
//            return "/productDetail/productDetail-view-add";
//        }
        // Tiếp tục xử lý cập nhật thông tin sản phẩm
        if (bindingResult.hasErrors()) {
            model.addAttribute("proDetail", productDetailDAO);
            model.addAttribute("capacities", capacityService.getAll());
            model.addAttribute("color", colorService.getAll());
            model.addAttribute("manufacturer", manufactureService.getAll());
            model.addAttribute("category", categoryService.getAll());
            model.addAttribute("battery", batteryService.getAll());
            model.addAttribute("chip", chipService.getAll());
            model.addAttribute("ram", ramService.getAll());
            model.addAttribute("screen", screenService.getAll());
            model.addAttribute("product", productService.getAll());
            return "productDetail/productDetail-view-add";
        }
        //Lưu ý: khi update phải kiểm tra imei đó đã tồn tại trên dòng sản phẩm khác chưa
        //       kiểm tra mã imei có sai, vượt quá ký tự trong sql hay không
        try {
            // tạo đối tượng mới cho ProductDetail và set lại toàn bộ giá trị
            ProductDetail productDetail = new ProductDetail();
            // đọc hình ảnh
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productDetailDAO.getImages().getOriginalFilename());
            Files.write(fileNameAndPath, productDetailDAO.getImages().getBytes());

            productDetail.setCode(productDetailDAO.getCode());
            productDetail.setName(productDetailDAO.getName());
            productDetail.setCapacity(productDetailDAO.getCapacity());
            productDetail.setColor(productDetailDAO.getColor());
            productDetail.setManufacture(productDetailDAO.getManufacture());
            productDetail.setCategory(productDetailDAO.getCategory());
            productDetail.setProduct(productDetailDAO.getProduct());
            productDetail.setChip(productDetailDAO.getChip());
            productDetail.setScreen(productDetailDAO.getScreen());
            productDetail.setRam(productDetailDAO.getRam());
            productDetail.setPersonCreate(productDetailDAO.getPersonCreate());
            productDetail.setPrice(productDetailDAO.getPrice());
            productDetail.setImages("/images/" + productDetailDAO.getImages().getOriginalFilename());

            int quantity = 0; // Số lượng bổ sung từ file IMEI
            List<String> imeis = new ArrayList<>();
            if (imeiFile != null && !imeiFile.isEmpty()) {
                // Đọc file IMEI và thực hiện các thao tác cập nhật
                imeis = ExcelUtil.extractImeisFromExcel(imeiFile);
            }

            //list imei không phải của productDetail creat
            List<CharSequence> listImeifindByIdProductDetail = imeiService.getImeiByIdProductDetail(productDetail.getId());

            // find ra đối tượng product
            Product product = productService.findById(productDetail.getProduct().getId());

            // getAll code imei
            List<CharSequence> imeiList = imeiService.findByCodeImei(productDetail.getId());

            //list code imei vượt qua 20 ký tự (tạo list rỗng để tý add vào)
            List<String> imeiIsBigger = new ArrayList<>();

            //list code imei đã tồn tại trên dòng sp khác (tạo list rỗng để tý add vào)
            List<Imei> listImeiAlreadyExistsElsewhere = new ArrayList<>();


            int listImeisSize = imeis.size();
            int listSizeImeifindByIdProductDetail = listImeifindByIdProductDetail.size();

            for (int i = 0; i < listImeisSize; i++) {
                //add imei vượt qua 20 ký tự ra list mới
                if (imeis.get(i).trim().length() > 20) {
                    // nếu vượt quá ký tự thì add voà list xong thông báo
                    imeiIsBigger.add(imeis.get(i));
                }
                // kiểm tra imei đã tồn tại trên dòng máy khác hay chưa hay chưa
                if (imeiIsBigger.isEmpty()) {
                    if (listImeifindByIdProductDetail.contains(imeis.get(i).trim())) {
                        //nếu trùng thì add imei đó vào list xong thông báo
                        List<Imei> findImei = imeiService.findByCode(imeis.get(i).trim());
                        listImeiAlreadyExistsElsewhere.add(findImei.get(0));
                    }
                }
            }
//            System.out.println(listImeiAlreadyExistsElsewhere.size() + " =======================>" + listImeiAlreadyExistsElsewhere.toString());

            //nếu thoả mãn điều kiện thì cho  add imei mới
            if (imeiIsBigger.isEmpty() && listImeiAlreadyExistsElsewhere.isEmpty()) {

                productDetailService.addProductDetail(productDetail);
                // duyệt số lượng imei teong excel
                for (int i = 0; i < listImeisSize; i++) {
                    if (imeis.get(i).trim().isEmpty()) {
                        continue;
                    }
//                    if (imeiList.contains(imeis.get(i).trim())) {
//                        List<Imei> findImei = imeiService.findByCode(imeis.get(i).trim());
//                        findImei.get(0).setCode(imeis.get(i).trim());
//                        imeiService.update(findImei.get(0));
                     else {
                        quantity = quantity + 1;
                        Imei newImei = new Imei();
                        newImei.setCode(imeis.get(i).trim());
                        newImei.setProductDetail(productDetail);
                        imeiService.addImei(newImei);
                    }
                }
                // Cập nhật số lượng sản phẩm
                int currentQuantity = product.getQuantity();
                int updatedQuantity = currentQuantity + quantity;
                product.setQuantity(updatedQuantity);
                productService.update(product);

                productDetail.setQuantity(quantity);
                productDetailService.updateProduct(productDetail);
                return "redirect:/productDetails/display";
            } else {
                //các mục trên form
                model.addAttribute("proDetail", productDetailDAO);
                model.addAttribute("capacities", capacityService.getAll());
                model.addAttribute("color", colorService.getAll());
                model.addAttribute("manufacturer", manufactureService.getAll());
                model.addAttribute("category", categoryService.getAll());
                model.addAttribute("battery", batteryService.getAll());
                model.addAttribute("chip", chipService.getAll());
                model.addAttribute("ram", ramService.getAll());
                model.addAttribute("screen", screenService.getAll());
                model.addAttribute("product", productService.getAll());

                //thông báo lỗi vượt quá 20 ký tự
                model.addAttribute("errorImeiIsGreaterThan20Characters", !imeiIsBigger.isEmpty());
                model.addAttribute("listErrorImeiIsGreaterThan20Characters", imeiIsBigger);

                //thông báo lỗi imei đã có trên dòng máy khác
                model.addAttribute("errorImeiAlreadyExistsElsewhere", !listImeiAlreadyExistsElsewhere.isEmpty());
                model.addAttribute("listErrorImeiAlreadyExistsElsewhere", listImeiAlreadyExistsElsewhere);


                return "productDetail/productDetail-view-add";
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi đọc file IMEI.");
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
        //lấy ra đối tượng productDetail theo Id
        ProductDetail productDetail = productDetailService.getOne(id);

        //Khởi tạo đối tượng ProductDetailDAO
        ProductDetailDAO productDetailDAO = new ProductDetailDAO();
        //set dữ liệu cho đối tượng ProductDetailDAO
        productDetailDAO.setId(productDetail.getId());
        productDetailDAO.setCode(productDetail.getCode());
        productDetailDAO.setName(productDetail.getName());
        productDetailDAO.setCapacity(productDetail.getCapacity());
        productDetailDAO.setColor(productDetail.getColor());
        productDetailDAO.setManufacture(productDetail.getManufacture());
        productDetailDAO.setCategory(productDetail.getCategory());
        productDetailDAO.setProduct(productDetail.getProduct());
        productDetailDAO.setChip(productDetail.getChip());
        productDetailDAO.setScreen(productDetail.getScreen());
        productDetailDAO.setRam(productDetail.getRam());
        productDetailDAO.setPersonCreate(productDetail.getPersonCreate());
        productDetailDAO.setPrice(productDetail.getPrice());

//        // Chuỗi cần chuyển đổi thành MultipartFile
//        String content = productDetail.getImages();
//        //Tạo đối tượng MultipartFile giả lập
//        MultipartFile multipartFile = new MockMultipartFile("filename.txt", "filename.txt", "text/plain", content.getBytes());
//        productDetailDAO.setImages(multipartFile);
//        System.out.println(productDetailDAO.getImages() + "===============================>" + productDetail.getImages());

        model.addAttribute("proDetail", productDetailDAO);
//        model.addAttribute("proDetailImage", productDetail.getImages());

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
    public String updateProductDetail(Model model, @Valid @ModelAttribute("proDetail") ProductDetailDAO productDetailDAO,
                                      BindingResult result,
                                      @RequestParam(value = "imeiFile", required = false) MultipartFile imeiFile) {

        // Tiếp tục xử lý cập nhật thông tin sản phẩm
        if (result.hasErrors()) {
            model.addAttribute("proDetail", productDetailDAO);
            model.addAttribute("capacities", capacityService.getAll());
            model.addAttribute("color", colorService.getAll());
            model.addAttribute("manufacturer", manufactureService.getAll());
            model.addAttribute("category", categoryService.getAll());
            model.addAttribute("battery", batteryService.getAll());
            model.addAttribute("chip", chipService.getAll());
            model.addAttribute("ram", ramService.getAll());
            model.addAttribute("screen", screenService.getAll());
            model.addAttribute("product", productService.getAll());
            return "productDetail/view-update";
        }
        //Lưu ý: khi update phải kiểm tra imei đó đã tồn tại trên dòng sản phẩm khác chưa
        //       kiểm tra mã imei có sai, vượt quá ký tự trong sql hay không
        try {
            //lấy ra đối tượng productDetail theo id có trên form thông qua productDetailDAO
            ProductDetail productDetail = productDetailService.getOne(productDetailDAO.getId());

            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productDetailDAO.getImages().getOriginalFilename());
            Files.write(fileNameAndPath, productDetailDAO.getImages().getBytes());

            productDetail.setCode(productDetailDAO.getCode());
            productDetail.setName(productDetailDAO.getName());
            productDetail.setCapacity(productDetailDAO.getCapacity());
            productDetail.setColor(productDetailDAO.getColor());
            productDetail.setManufacture(productDetailDAO.getManufacture());
            productDetail.setCategory(productDetailDAO.getCategory());
            productDetail.setProduct(productDetailDAO.getProduct());
            productDetail.setChip(productDetailDAO.getChip());
            productDetail.setScreen(productDetailDAO.getScreen());
            productDetail.setRam(productDetailDAO.getRam());
            productDetail.setPersonCreate(productDetailDAO.getPersonCreate());
            productDetail.setPrice(productDetailDAO.getPrice());
            productDetail.setImages("/images/" + productDetailDAO.getImages().getOriginalFilename());

            int quantity = 0; // Số lượng bổ sung từ file IMEI
            List<String> imeis = new ArrayList<>();
            if (imeiFile != null && !imeiFile.isEmpty()) {
                // Đọc file IMEI và thực hiện các thao tác cập nhật
                imeis = ExcelUtil.extractImeisFromExcel(imeiFile);
            }

            //list imei không phải của productDetail update
            List<CharSequence> listImeifindByIdProductDetail = imeiService.getImeiByIdProductDetail(productDetail.getId());
//            System.out.println(listImeifindByIdProductDetail.toString() + "======================================= 1");
//            System.out.println(productDetail.getId() + "======================================= 1.1");

            // find ra đối tượng product
            Product product = productService.findById(productDetail.getProduct().getId());

            // getAll code imei
            List<CharSequence> imeiList = imeiService.findByCodeImei(productDetail.getId());
//            System.out.println(imeiList.toString() + "======================================= 2");


            //list code imei vượt qua 20 ký tự (tạo list rỗng để tý add vào)
            List<String> imeiIsBigger = new ArrayList<>();

            //list code imei đã tồn tại trên dòng sp khác (tạo list rỗng để tý add vào)
            List<Imei> listImeiAlreadyExistsElsewhere = new ArrayList<>();


            int listImeisSize = imeis.size();
            int listSizeImeifindByIdProductDetail = listImeifindByIdProductDetail.size();

            for (int i = 0; i < listImeisSize; i++) {
                //add imei vượt qua 20 ký tự ra list mới
                if (imeis.get(i).trim().length() > 20) {
                    // nếu vượt quá ký tự thì add voà list xong thông báo
                    imeiIsBigger.add(imeis.get(i));
                }
                // kiểm tra imei đã tồn tại trên dòng máy khác hay chưa hay chưa
                if (imeiIsBigger.isEmpty()) {
                    if (listImeifindByIdProductDetail.contains(imeis.get(i).trim())) {
                        //nếu trùng thì add imei đó vào list xong thông báo
                        List<Imei> findImei = imeiService.findByCode(imeis.get(i).trim());
                        listImeiAlreadyExistsElsewhere.add(findImei.get(0));
                    }
                }
            }
//            System.out.println(listImeiAlreadyExistsElsewhere.size() + " =======================>" + listImeiAlreadyExistsElsewhere.toString());

            //nếu thoả mãn điều kiện thì cho update hoặc add imei mới
            if (imeiIsBigger.isEmpty() && listImeiAlreadyExistsElsewhere.isEmpty()) {


                // duyệt số lượng imei teong excel
                for (int i = 0; i < listImeisSize; i++) {
                    if (imeis.get(i).trim().isEmpty()) {
                        continue;
                    }
                    if (imeiList.contains(imeis.get(i).trim())) {
                        List<Imei> findImei = imeiService.findByCode(imeis.get(i).trim());
                        findImei.get(0).setCode(imeis.get(i).trim());
                        imeiService.update(findImei.get(0));
                    } else {
                        quantity = quantity + 1;
                        Imei newImei = new Imei();
                        newImei.setCode(imeis.get(i).trim());
                        newImei.setProductDetail(productDetail);
                        imeiService.addImei(newImei);
                    }
                }
                // Cập nhật số lượng sản phẩm
                int currentQuantityProduct = product.getQuantity();
                int updatedQuantityProduct = currentQuantityProduct + quantity;
                product.setQuantity(updatedQuantityProduct);
                productService.update(product);

                int currentQuantityProductDetail = productDetail.getQuantity();
                int updatedQuantityProductDetail = currentQuantityProductDetail + quantity;
                productDetail.setQuantity(updatedQuantityProductDetail);
                productDetailService.updateProduct(productDetail);
                return "redirect:/productDetails/display";
            } else {
                //các mục trên form
                model.addAttribute("proDetail", productDetailDAO);
                model.addAttribute("capacities", capacityService.getAll());
                model.addAttribute("color", colorService.getAll());
                model.addAttribute("manufacturer", manufactureService.getAll());
                model.addAttribute("category", categoryService.getAll());
                model.addAttribute("battery", batteryService.getAll());
                model.addAttribute("chip", chipService.getAll());
                model.addAttribute("ram", ramService.getAll());
                model.addAttribute("screen", screenService.getAll());
                model.addAttribute("product", productService.getAll());

                //thông báo lỗi vượt quá 20 ký tự
                model.addAttribute("errorImeiIsGreaterThan20Characters", !imeiIsBigger.isEmpty());
                model.addAttribute("listErrorImeiIsGreaterThan20Characters", imeiIsBigger);

                //thông báo lỗi imei đã có trên dòng máy khác
                model.addAttribute("errorImeiAlreadyExistsElsewhere", !listImeiAlreadyExistsElsewhere.isEmpty());
                model.addAttribute("listErrorImeiAlreadyExistsElsewhere", listImeiAlreadyExistsElsewhere);


                return "productDetail/view-update";
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi đọc file IMEI.");
            return "/productDetail/view-update";
        }
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
