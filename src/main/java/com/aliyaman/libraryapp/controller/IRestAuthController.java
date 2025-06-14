package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.*;

public interface IRestAuthController {

    public UserDto register(RegisterRequest request);

    public AuthResponse login(LoginRequest request);

    public AuthResponse refreshToken(RefreshTokenRequest request);
}
