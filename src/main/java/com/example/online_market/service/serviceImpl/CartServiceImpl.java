package com.example.online_market.service.serviceImpl;

import com.example.online_market.entity.Cart;
import com.example.online_market.entity.Products;
import com.example.online_market.entity.User;
import com.example.online_market.repository.CartRepo;
import com.example.online_market.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    @Override
    public void createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(new ArrayList<>());
        cartRepo.save(cart);
    }

    @Override
    public Optional<Cart> findByUserId(int userId) {
        return cartRepo.findByUserId(userId);
    }

    @Override
    public void addProduct(Products products, User user) {
        Optional<Cart> optCart = cartRepo.findByUserId(user.getId());
        if (optCart.isPresent()) {
            Cart cart = optCart.get();
            cart.getProducts().add(products);
            cartRepo.save(cart);
        }
    }

    @Override
    public double sumOfCart(int userId) {
        double sum = 0;
        Optional<Cart> optionalCart = cartRepo.findByUserId(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<Products> products = cart.getProducts();
            for (Products product : products) {
                sum += product.getPrice();
            }
        }
        return sum;
    }

    @Override
    public List<Products> getProductsByUserId(int userId) {
        List<Products> products = new ArrayList<>();
        Optional<Cart> byUserId = findByUserId(userId);
        if (byUserId.isPresent()) {
            products = byUserId.get().getProducts();
        }
        return products;
    }

    @Override
    public void deleteProductById(int productId, int userId) {

        Optional<Cart> byUserId = findByUserId(userId);
        if (byUserId.isPresent()) {
            Cart cart = byUserId.get();
            List<Products> products = cart.getProducts();
            products.removeIf(product -> product.getId() == productId);
            cart.setProducts(products);
            cartRepo.save(cart);
        }

    }

    @Override
    public void delete(Cart cart) {
        cartRepo.delete(cart);
    }
}
