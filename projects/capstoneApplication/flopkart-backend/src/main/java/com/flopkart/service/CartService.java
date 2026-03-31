package com.flopkart.service;

import com.flopkart.dto.CartItemRequest;
import com.flopkart.dto.CartItemResponse;
import com.flopkart.dto.CartQuantityRequest;
import com.flopkart.exception.ResourceNotFoundException;
import com.flopkart.model.CartItem;
import com.flopkart.model.Product;
import com.flopkart.model.User;
import com.flopkart.repository.CartItemRepository;
import com.flopkart.repository.ProductRepository;
import com.flopkart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // ─── Queries ─────────────────────────────────────────────────────────────

    public List<CartItemResponse> getCart(String username) {
        User user = getUser(username);
        return cartItemRepository.findByUser(user).stream()
                .map(this::toResponse)
                .toList();
    }

    // ─── Commands ────────────────────────────────────────────────────────────

    /**
     * Adds a product to the cart or replaces its quantity if it already exists.
     */
    @Transactional
    public CartItemResponse addOrSetItem(String username, CartItemRequest request) {
        User user = getUser(username);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + request.getProductId() + " not found"));

        Optional<CartItem> existing = cartItemRepository.findByUserAndProductId(user, product.getId());

        CartItem cartItem;
        if (existing.isPresent()) {
            cartItem = existing.get();
            cartItem.setQuantity(request.getQuantity());
        } else {
            cartItem = CartItem.builder()
                    .user(user)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
        }

        return toResponse(cartItemRepository.save(cartItem));
    }

    /**
     * Updates only the quantity of an existing cart item.
     */
    @Transactional
    public CartItemResponse updateQuantity(String username, Long productId, CartQuantityRequest request) {
        User user = getUser(username);
        CartItem cartItem = cartItemRepository.findByUserAndProductId(user, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart item for product " + productId + " not found"));

        cartItem.setQuantity(request.getQuantity());
        return toResponse(cartItemRepository.save(cartItem));
    }

    /**
     * Removes a single product from the cart.
     */
    @Transactional
    public void removeItem(String username, Long productId) {
        User user = getUser(username);
        if (cartItemRepository.findByUserAndProductId(user, productId).isEmpty()) {
            throw new ResourceNotFoundException("Cart item for product " + productId + " not found");
        }
        cartItemRepository.deleteByUserAndProductId(user, productId);
    }

    /**
     * Clears the entire cart for the authenticated user.
     */
    @Transactional
    public void clearCart(String username) {
        User user = getUser(username);
        cartItemRepository.deleteByUser(user);
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    private CartItemResponse toResponse(CartItem item) {
        return CartItemResponse.builder()
                .productId(item.getProduct().getId())
                .title(item.getProduct().getTitle())
                .price(item.getProduct().getPrice())
                .image(item.getProduct().getImage())
                .quantity(item.getQuantity())
                .build();
    }
}
