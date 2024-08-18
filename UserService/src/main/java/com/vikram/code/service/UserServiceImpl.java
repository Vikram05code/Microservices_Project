package com.vikram.code.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.code.dtos.UserDto;
import com.vikram.code.entities.User;
import com.vikram.code.exceptions.ResourceNotFoundException;
import com.vikram.code.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

   private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

   @Autowired
   private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {

        ObjectMapper objectMapper = new ObjectMapper();
        String randomUserId = UUID.randomUUID().toString();
        User user = objectMapper.convertValue(userDto, User.class);
        user.setUserId(randomUserId);
        logger.info("User Copied From UserDto: ", user);
        if(user != null) {
            userRepository.save(user);
        }
        else {
            throw new RuntimeException();
        }
        userDto = objectMapper.convertValue(user, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUserById(String userId) {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Resource Not Found for Given Id: "+userId));
        UserDto userDto = objectMapper.convertValue(user, UserDto.class);

        return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        if(userList != null){
            for(User user : userList){
              UserDto userDto = objectMapper.convertValue(user, UserDto.class);
              userDtoList.add(userDto);
            }
        }

        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with given Id: "+userId));
        if(user != null){
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setAbout(userDto.getAbout());

            userRepository.save(user);
        }
        userDto = objectMapper.convertValue(user, UserDto.class);

        return userDto;
    }

    @Override
    public String deleteUser(String userId) {
        String message = "User Deletion Failed";
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with given Id: "+userId));
        if(user != null){
            userRepository.delete(user);
             message = "User Deleted Successfully";
        }


        return message;
    }
}
