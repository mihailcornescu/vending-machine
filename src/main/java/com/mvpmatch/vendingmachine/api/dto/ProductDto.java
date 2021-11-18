package com.mvpmatch.vendingmachine.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private int amountAvailable;
    private BigDecimal cost;
    private String productName;
    private int sellerId;

}
