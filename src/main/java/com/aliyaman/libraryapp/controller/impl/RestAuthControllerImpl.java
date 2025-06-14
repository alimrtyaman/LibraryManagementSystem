package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestAuthController;
import com.aliyaman.libraryapp.dto.*;
import com.aliyaman.libraryapp.service.IAuthService;
import com.aliyaman.libraryapp.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    private final IAuthService authService;
    private final IRefreshTokenService refreshTokenService;


    @Autowired
    public RestAuthControllerImpl(IAuthService authService, IRefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/register")
    @Override
    public UserDto register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @Override
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.refreshToken(request);
    }
}
