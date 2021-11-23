package com.mvpmatch.vendingmachine.service.impl;

import com.mvpmatch.vendingmachine.api.dto.ProductDto;
import com.mvpmatch.vendingmachine.persistence.ProductRepository;
import com.mvpmatch.vendingmachine.persistence.entity.ProductEntity;
import com.mvpmatch.vendingmachine.service.ProductService;
import com.mvpmatch.vendingmachine.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    @Override
    public void addProduct(ProductDto productDto) {
        ProductEntity productEntity = mapper.toProductEntity(productDto);
        repository.save(productEntity);
    }

    @Override
    public ProductDto getProductById(String id) {
        ProductEntity productEntity = repository.getById(Long.parseLong(id));
        return mapper.toProductDto(productEntity);
    }


}
