package com.mvpmatch.vendingmachine.service.impl;

import com.mvpmatch.vendingmachine.api.dto.UserDto;
import com.mvpmatch.vendingmachine.persistence.UserRepository;
import com.mvpmatch.vendingmachine.persistence.entity.UserEntity;
import com.mvpmatch.vendingmachine.service.UserService;
import com.mvpmatch.vendingmachine.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public void addUser(UserDto userDto) {
        UserEntity entity = mapper.toUserEntity(userDto);
        repository.save(entity);
    }
}
