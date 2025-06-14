package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.AuthResponse;
import com.aliyaman.libraryapp.dto.RefreshTokenRequest;
import com.aliyaman.libraryapp.entity.CustomUserDetails;
import com.aliyaman.libraryapp.entity.RefreshToken;
import com.aliyaman.libraryapp.entity.User;
import com.aliyaman.libraryapp.repository.RefreshTokenRepository;
import com.aliyaman.libraryapp.security.JwtService;
import com.aliyaman.libraryapp.service.IRefreshTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }


    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if (optional.isEmpty()){
            throw new IllegalArgumentException("Token is invalid");
        }
        RefreshToken refreshToken = optional.get();
        if (!isRefreshTokenExpired(refreshToken.getExpireDate())){
            throw new RuntimeException("Token is expired");
        }
        String accessToken = jwtService.generateToken(new CustomUserDetails(refreshToken.getUser()));
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));

        return new AuthResponse(accessToken , refreshToken.getRefreshToken());
    }





    public RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setUser(user);
        return refreshToken;
    }

    public boolean isRefreshTokenExpired(Date expiredDate){
        return new Date().before(expiredDate);
    }
}
