package com.cart.shopcart.controller;

import com.cart.shopcart.model.ShopCart;
import com.cart.shopcart.model.ShopProduct;
import com.cart.shopcart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShopCartController {
    private final CartService cartService;

    @GetMapping("/product/{productId}")
    public ShopProduct findOneProductById(@PathVariable long productId) {

        log.info("Calling productExample.getText() with Sometext: {}", productId);
        ShopProduct shopProduct = cartService.getOneProduct(productId);

        if (shopProduct == null) {
            log.error("ProductExample returned null for Sometext");
            throw new IllegalStateException("The returned Sometext is null");
        }

        log.info("Received response: id={}, name={}, nutrition={}, calories={}", shopProduct.getId(), shopProduct.getProductName(), shopProduct.getNutritionalInfo(), shopProduct.getCalories());
        return shopProduct;
    }

    @GetMapping("/{cartId}")
    public ShopCart findOneCartById(@PathVariable Long cartId) {
        return cartService.getOneShopCart(cartId);
    }

    @PostMapping("/create")
    public ShopCart createNewCart() {
        return cartService.createNewCart();
    }
    @PostMapping("/add/{cartId}/{productId}")
    public void addProductToCart(@PathVariable Long cartId,@PathVariable Long productId) {
        cartService.addProductToCart(cartId, productId);
    }
}