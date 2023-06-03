package com.example.online_market.contraller;

import com.example.online_market.entity.Products;
import com.example.online_market.security.CurrentUser;
import com.example.online_market.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("products_id") Products products,
                             @AuthenticationPrincipal CurrentUser user) {
        if (cartService.findByUserId(user.getUser().getId()).isEmpty()) {
            cartService.createCart(user.getUser());
        }
        cartService.addProduct(products, user.getUser());
        return "redirect:/";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("product_id") int productId,
                                @AuthenticationPrincipal CurrentUser currentUser){
            cartService.deleteProductById(productId,currentUser.getUser().getId());
        return "redirect:/cart/single/"+currentUser.getUser().getId();
    }

    @GetMapping("/single/{id}")
    public String singleCart(@PathVariable("id") int userId,
                             ModelMap modelMap) {
        modelMap.addAttribute("products",cartService.getProductsByUserId(userId));
        return "cart";
    }

}
