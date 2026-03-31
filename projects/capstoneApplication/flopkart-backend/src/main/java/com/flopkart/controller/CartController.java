package com.flopkart.controller;

import com.flopkart.dto.CartItemRequest;
import com.flopkart.dto.CartItemResponse;
import com.flopkart.dto.CartQuantityRequest;
import com.flopkart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * GET /api/cart
     * Returns all cart items for the authenticated user.
     */
    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getCart(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCart(userDetails.getUsername()));
    }

    /**
     * POST /api/cart
     * Adds a product to the cart, or sets its quantity if it already exists.
     */
    @PostMapping
    public ResponseEntity<CartItemResponse> addItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.addOrSetItem(userDetails.getUsername(), request));
    }

    /**
     * PUT /api/cart/{productId}
     * Updates the quantity of a specific cart item.
     */
    @PutMapping("/{productId}")
    public ResponseEntity<CartItemResponse> updateQuantity(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId,
            @Valid @RequestBody CartQuantityRequest request) {
        return ResponseEntity.ok(cartService.updateQuantity(userDetails.getUsername(), productId, request));
    }

    /**
     * DELETE /api/cart/{productId}
     * Removes a single product from the cart.
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        cartService.removeItem(userDetails.getUsername(), productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * DELETE /api/cart
     * Clears all items from the authenticated user's cart.
     */
    @DeleteMapping
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        cartService.clearCart(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
