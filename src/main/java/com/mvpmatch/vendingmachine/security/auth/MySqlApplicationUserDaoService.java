package com.mvpmatch.vendingmachine.security.auth;

import com.mvpmatch.vendingmachine.persistence.UserRepository;
import com.mvpmatch.vendingmachine.persistence.entity.UserEntity;
import com.mvpmatch.vendingmachine.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("mysql")
@RequiredArgsConstructor
public class MySqlApplicationUserDaoService implements ApplicationUserDao {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            ApplicationUser applicationUser = mapper.toApplicationUser(userEntity);
            return Optional.of(applicationUser);
        } else {
            throw new RuntimeException(String.format("User %s does not exist.", username));
        }
    }
}
