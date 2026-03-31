package com.flopkart.service;

import com.flopkart.dto.ProductRequest;
import com.flopkart.dto.ProductResponse;
import com.flopkart.exception.ResourceNotFoundException;
import com.flopkart.model.Product;
import com.flopkart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    // ─── Queries ─────────────────────────────────────────────────────────────

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }

    public List<ProductResponse> getProductsByCategory(String category) {
        if (!productRepository.existsByCategory(category)) {
            throw new ResourceNotFoundException("Category '" + category + "' not found");
        }
        return productRepository.findByCategory(category).stream()
                .map(this::toResponse)
                .toList();
    }

    // ─── Commands ────────────────────────────────────────────────────────────

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(request.getCategory())
                .image(request.getImage())
                .build();
        return toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setImage(request.getImage());

        return toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(product);
        return toResponse(product);
    }

    // ─── Mapper ──────────────────────────────────────────────────────────────

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .image(product.getImage())
                .rating(ProductResponse.Rating.builder()
                        .rate(product.getRatingRate())
                        .count(product.getRatingCount())
                        .build())
                .build();
    }
}
