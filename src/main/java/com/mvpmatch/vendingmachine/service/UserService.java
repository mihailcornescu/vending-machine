package com.mvpmatch.vendingmachine.service;

import com.mvpmatch.vendingmachine.api.dto.UserDto;

public interface UserService {

    void addUser(UserDto userDto);
    UserDto getUser(String username);
}
