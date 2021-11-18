package com.mvpmatch.vendingmachine.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private int amountAvailable;
    private BigDecimal cost;
    private String productName;
    private int sellerId;
}
