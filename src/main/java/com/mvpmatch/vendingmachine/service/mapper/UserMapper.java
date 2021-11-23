package com.mvpmatch.vendingmachine.service.mapper;

import com.mvpmatch.vendingmachine.api.dto.UserDto;
import com.mvpmatch.vendingmachine.persistence.entity.UserEntity;
import com.mvpmatch.vendingmachine.security.ApplicationUserRole;
import com.mvpmatch.vendingmachine.security.auth.ApplicationUser;
import org.apache.commons.lang3.EnumUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "grantedAuthorities", expression = "java(getAuthorities(userEntity.getRoles()))")
    public abstract ApplicationUser toApplicationUser(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userDto.getPassword()))")
    public abstract UserEntity toUserEntity(UserDto userDto);

    public abstract UserDto toUserDto(UserEntity userEntity);

    protected Set<SimpleGrantedAuthority> getAuthorities(String authoritiesString) {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        String[] authoritiesStrings = authoritiesString.split(",");
        for(String authorityString : authoritiesStrings) {
            if (authorityString.startsWith("ROLE_")) {
                String enumString = authorityString.substring(5);
                if (EnumUtils.isValidEnum(ApplicationUserRole.class, enumString)) {
                    simpleGrantedAuthorities.addAll(ApplicationUserRole.valueOf(enumString).getGrantedAuthorities());
                }
            }
        }
        return simpleGrantedAuthorities;
    }

}
