package com.mvpmatch.vendingmachine.api.dto;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

}
