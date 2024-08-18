package com.vikram.code.service;

import com.vikram.code.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> getAllUser();

    UserDto updateUser(UserDto userDto, String userId);

    String deleteUser(String userId);
}
