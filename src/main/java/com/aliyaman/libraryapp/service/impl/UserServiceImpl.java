package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.UserDto;
import com.aliyaman.libraryapp.entity.User;
import com.aliyaman.libraryapp.mapper.UserMapper;
import com.aliyaman.libraryapp.repository.UserRepository;
import com.aliyaman.libraryapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository theuserRepository,UserMapper theUserMApper){
        this.userRepository=theuserRepository;
        this.userMapper = theUserMApper;
    }

    @Override
    public UserDto findUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()){
            return null;
        }
        User user = optional.get();
        return userMapper.toDto(user);
    }

    public boolean deleteUser(Long id){
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UserDto> getAllUsers() {

      return   userRepository.findAll()
              .stream()
              .map(userMapper::toDto)
              .collect(Collectors.toList());

    }
}
