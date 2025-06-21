package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.AuthResponse;
import com.aliyaman.libraryapp.dto.LoginRequest;
import com.aliyaman.libraryapp.dto.RegisterRequest;
import com.aliyaman.libraryapp.dto.UserDto;
import com.aliyaman.libraryapp.entity.CustomUserDetails;
import com.aliyaman.libraryapp.entity.RefreshToken;
import com.aliyaman.libraryapp.entity.Role;
import com.aliyaman.libraryapp.entity.User;
import com.aliyaman.libraryapp.mapper.UserMapper;
import com.aliyaman.libraryapp.repository.RefreshTokenRepository;
import com.aliyaman.libraryapp.repository.RoleRepository;
import com.aliyaman.libraryapp.repository.UserRepository;
import com.aliyaman.libraryapp.security.JwtService;
import com.aliyaman.libraryapp.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationProvider authenticationProvider;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationProvider authenticationProvider, UserRepository userRepository, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, RefreshTokenServiceImpl refreshTokenService, RefreshTokenRepository refreshTokenRepository, RoleRepository roleRepository) {
        this.authenticationProvider = authenticationProvider;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto register(RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setSurName(request.getSurName());
        user.setEmail(request.getEmail());
        user.setCountry(request.getCountry());

        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new IllegalArgumentException("Role not found")
        );
        user.setRoles(List.of(userRole));

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try{
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not exist"));
            CustomUserDetails userDetails = new CustomUserDetails(user);
            String token = jwtService.generateToken(userDetails);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
            refreshTokenRepository.save(refreshToken);
            return new AuthResponse(token , refreshToken.getRefreshToken());
        }catch (Exception e){
            throw new RuntimeException("Invalid user name and Password");
        }
    }


}
