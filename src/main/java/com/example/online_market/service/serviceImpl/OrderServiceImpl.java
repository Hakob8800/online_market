package com.example.online_market.service.serviceImpl;

import com.example.online_market.entity.Cart;
import com.example.online_market.entity.Orders;
import com.example.online_market.entity.Products;
import com.example.online_market.entity.User;
import com.example.online_market.repository.OrderRepo;
import com.example.online_market.service.CartService;
import com.example.online_market.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderRepo orderRepo;

    @Override
    @Transactional
    public void addOrder(User user) {
        Optional<Cart> byUserId = cartService.findByUserId(user.getId());
        Orders order = new Orders();
        if(byUserId.isPresent()){
            Cart cart = byUserId.get();
            List<Products> products = cart.getProducts();
            order.setProducts(products);
            order.setDateTime(LocalDateTime.now());
            order.setUser(user);
            cartService.delete(cart);
            orderRepo.save(order);
        }
    }

    @Override
    public void save(Orders order) {
        orderRepo.save(order);
    }

    @Override
    public List<Orders> findAll() {
        return orderRepo.findAll();
    }

}
