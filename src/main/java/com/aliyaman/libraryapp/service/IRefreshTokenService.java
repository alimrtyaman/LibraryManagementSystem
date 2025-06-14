package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.AuthResponse;
import com.aliyaman.libraryapp.dto.RefreshTokenRequest;

public interface IRefreshTokenService {

    public AuthResponse refreshToken(RefreshTokenRequest request);
}
