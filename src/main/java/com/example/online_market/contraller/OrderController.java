package com.example.online_market.contraller;

import com.example.online_market.entity.Orders;
import com.example.online_market.security.CurrentUser;
import com.example.online_market.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/add")
    public String addOrder(@AuthenticationPrincipal CurrentUser currentUser){
        orderService.addOrder(currentUser.getUser());
        return "redirect:/";
    }

    @GetMapping("/all")
    public String ordersPage(ModelMap modelMap){
        List<Orders> orders = orderService.findAll();
        modelMap.addAttribute("orders",orders);
        return "orders";
    }

}
