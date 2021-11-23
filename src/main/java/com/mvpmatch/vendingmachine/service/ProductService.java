package com.mvpmatch.vendingmachine.service;

import com.mvpmatch.vendingmachine.api.dto.ProductDto;

public interface ProductService {

    void addProduct(ProductDto productDto);
    ProductDto getProductById(String id);
}
