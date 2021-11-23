package com.mvpmatch.vendingmachine.api;

import com.mvpmatch.vendingmachine.api.dto.UserDto;
import com.mvpmatch.vendingmachine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.MessageFormat;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDto user) {
        userService.addUser(user);
        URI location = URI.create(MessageFormat.format("/api/v1/users/{0}", user.getUsername()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        UserDto userDto = userService.getUser(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/by-id/{username}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String username) {
        UserDto userDto = userService.getUser(username);
        return ResponseEntity.ok(userDto);
    }

}
