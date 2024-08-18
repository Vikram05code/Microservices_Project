package com.vikram.code.service;

import com.vikram.code.dtos.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    UserDto updateUser(UserDto userDto, String userId);

    String deleteUser(String userId);
}
