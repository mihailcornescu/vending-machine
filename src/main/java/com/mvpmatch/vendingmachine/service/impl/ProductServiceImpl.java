package com.mvpmatch.vendingmachine.service.impl;

import com.mvpmatch.vendingmachine.api.dto.ProductDto;
import com.mvpmatch.vendingmachine.persistence.ProductRepository;
import com.mvpmatch.vendingmachine.persistence.entity.ProductEntity;
import com.mvpmatch.vendingmachine.service.ProductService;
import com.mvpmatch.vendingmachine.service.mapper.ProductMapper;
import com.mvpmatch.vendingmachine.utils.exceptions.InvalidInputException;
import com.mvpmatch.vendingmachine.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public void addProduct(ProductDto productDto) {
        repository.findFirstByProductName(productDto.getProductName())
                .ifPresent(x -> {
                            throw new InvalidInputException(String.format("There is already a product with name '%s'.", productDto.getProductName()));
                        });
        ProductEntity productEntity = mapper.toProductEntity(productDto);
        repository.save(productEntity);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Long id = productDto.getId();
        ProductEntity productEntity = getProductEntityById(id);
        productEntity = mapper.toProductEntity(productDto);
        repository.save(productEntity);
        return mapper.toProductDto(productEntity);
    }

    @Override
    public ProductDto deleteProduct(long id) {
        return null;
    }

    @Override
    public ProductDto getProductById(long id) {
        ProductEntity productEntity = getProductEntityById(id);
        return mapper.toProductDto(productEntity);
    }

    private ProductEntity getProductEntityById(Long id) {
        Optional<ProductEntity> optionalEntity = repository.findById(id);
        Optional.ofNullable(optionalEntity).orElseThrow(() -> {
            throw new NotFoundException(String.format("There is no product with id '%s'.", id));
        });
        return optionalEntity.get();
    }

}
