package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.RegisterRequest;
import com.aliyaman.libraryapp.dto.UserDto;

public interface IAuthService {

    public UserDto register(RegisterRequest request);
}
