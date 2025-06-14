package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.AuthResponse;
import com.aliyaman.libraryapp.dto.LoginRequest;
import com.aliyaman.libraryapp.dto.RegisterRequest;
import com.aliyaman.libraryapp.dto.UserDto;

public interface IAuthService {

    public UserDto register(RegisterRequest request);

    public AuthResponse login(LoginRequest request);
}
