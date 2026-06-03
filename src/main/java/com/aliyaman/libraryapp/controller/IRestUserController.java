package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.AssignRoleRequest;
import com.aliyaman.libraryapp.dto.ChangePasswordRequest;
import com.aliyaman.libraryapp.dto.UpdateUserRequest;
import com.aliyaman.libraryapp.dto.UserDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IRestUserController {

    public UserDto findUserById(Long id);

    public boolean deleteUser(Long id);

    public List<UserDto> getAllUsers();

    public String assignRoleToUser(AssignRoleRequest request);

    public UserDto updateUser(Long id, UpdateUserRequest request);

    UserDto updateMe(UpdateUserRequest request, Authentication authentication);

    String changeMyPassword(ChangePasswordRequest request, Authentication authentication);
}
