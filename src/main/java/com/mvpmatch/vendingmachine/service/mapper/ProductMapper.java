package com.mvpmatch.vendingmachine.service.mapper;

import com.mvpmatch.vendingmachine.api.dto.ProductDto;
import com.mvpmatch.vendingmachine.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toProductEntity(ProductDto productDto);
    ProductDto toProductDto(ProductEntity productEntity);
}
