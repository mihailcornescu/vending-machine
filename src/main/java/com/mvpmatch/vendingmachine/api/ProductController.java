package com.mvpmatch.vendingmachine.api;

import com.mvpmatch.vendingmachine.api.dto.ProductDto;
import com.mvpmatch.vendingmachine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.MessageFormat;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto product) {
        productService.addProduct(product);
        URI location = URI.create(MessageFormat.format("/api/v1/products/{0}", product.getId()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product) {
        ProductDto productDto = productService.updateProduct(product);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String productId) {
        ProductDto productDto = productService.deleteProduct(Long.parseLong(productId));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long productId) {
        ProductDto productDto = productService.getProductById(productId);
        return ResponseEntity.ok(productDto);
    }
}
