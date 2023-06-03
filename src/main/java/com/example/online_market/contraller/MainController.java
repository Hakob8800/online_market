package com.example.online_market.contraller;

import com.example.online_market.security.CurrentUser;
import com.example.online_market.service.CartService;
import com.example.online_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping(value = "/getImg", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImg(@RequestParam("image") String img) throws IOException {
        return productService.getImg(img);
    }

    @GetMapping("/")
    public String indexPage(ModelMap modelMap,
                            @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("products", productService.findAll());
        modelMap.addAttribute("sumOfCart", cartService.sumOfCart(currentUser.getUser().getId()));
        return "index";
    }

    @GetMapping("/customLogin")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/successLoginUrl")
    public String successLogin(){
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
