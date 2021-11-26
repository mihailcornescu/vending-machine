package com.mvpmatch.vendingmachine.service;

import com.mvpmatch.vendingmachine.api.dto.ProductDto;

public interface ProductService {

    void addProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    ProductDto deleteProduct(long id);
    ProductDto getProductById(long id);
}
