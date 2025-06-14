package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.RegisterRequest;
import com.aliyaman.libraryapp.dto.UserDto;
import com.aliyaman.libraryapp.entity.User;
import com.aliyaman.libraryapp.mapper.UserMapper;
import com.aliyaman.libraryapp.repository.UserRepository;
import com.aliyaman.libraryapp.security.JwtService;
import com.aliyaman.libraryapp.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationProvider authenticationProvider;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(AuthenticationProvider authenticationProvider, UserRepository userRepository, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
        this.authenticationProvider = authenticationProvider;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto register(RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setCountry(request.getCountry());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    
}
