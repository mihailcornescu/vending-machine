package com.mvpmatch.vendingmachine.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDto {

    private long id;
    private String username;
    private String password;
    private String roles;
    private BigDecimal deposit;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;
}
