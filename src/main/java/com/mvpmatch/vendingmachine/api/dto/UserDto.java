package com.mvpmatch.vendingmachine.api.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class UserDto {

    private String username;
    private String password;
    private String roles;
    private BigDecimal deposit;
}
