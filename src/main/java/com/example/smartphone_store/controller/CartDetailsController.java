package com.example.smartphone_store.controller;

import com.example.smartphone_store.entity.Cart;
import com.example.smartphone_store.entity.CartDetails;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.service.impl.CartDetailsService;
import com.example.smartphone_store.service.impl.CartService;
import com.example.smartphone_store.service.impl.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/cart-details")
public class CartDetailsController {

    @Autowired
    private ProductDetailServiceImpl productDetailService;

    @Autowired
    private CartDetailsService cartDetailsService;

    @Autowired
    private CartService cartService;

    @GetMapping(value = "/index")
    public String index(Model model) {
        Cart cart = this.cartService.findById(1L);
        model.addAttribute("cart", cart);
        return "pages/cart";
    }

    @PostMapping("/add/{name}")
    public String singleProduct(Model model, @PathVariable("name") String name,
                                @RequestParam(name = "capacity", required = false, defaultValue = "") String capacityReques,
                                @RequestParam(name = "color", required = false, defaultValue = "") String colorReques,
                                @RequestParam(name = "quantity") int quantity) {
        String capacityPathOf = "";
        String colorPathOf = "";

        if (capacityReques.isEmpty() && colorReques.isEmpty()) {
            List<ProductDetail> productDetailOrImeis =
                    productDetailService.findProductDetailByNameandImei(name);
            capacityPathOf = productDetailOrImeis.get(0).getCapacity().getName();
            capacityReques = capacityPathOf;
            model.addAttribute("capacityPathOf", capacityPathOf);
            colorPathOf = productDetailOrImeis.get(0).getColor().getName();
            colorReques = colorPathOf;
            model.addAttribute("colorPathOf", colorPathOf);

        }

        if (capacityReques != null) {
            capacityPathOf = capacityReques;
            model.addAttribute("capacityPathOf", capacityPathOf);
        }
        if (colorReques != null) {
            colorPathOf = colorReques;
            model.addAttribute("colorPathOf", colorPathOf);
        }

        ProductDetail productDetails = this.productDetailService.findProductDetailByNameAndCapacityAndColor(name, colorReques, capacityReques).get(0);
        CartDetails searchDetails = cartDetailsService.countProductDetails(productDetails.getId());
        if (searchDetails != null) {
            cartDetailsService.update(searchDetails.getId(), quantity);
            cartDetailsService.updateTotalMoney(searchDetails.getId(), searchDetails.getQuantity());
        } else {
            CartDetails cartDetails = new CartDetails();
            cartDetails.setId_productDetail(productDetails);
            cartDetails.setQuantity(quantity);
            cartDetails.setTotalMoney((long) (productDetails.getPrice() * quantity));
            cartDetailsService.add(cartDetails);
        }
        return "redirect:/cart-details/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteCart(@PathVariable(value = "id") Long id) {
        this.cartDetailsService.delete(id);
        return "redirect:/cart-details/index";
    }

}
