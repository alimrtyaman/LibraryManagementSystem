package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.AssignRoleRequest;
import com.aliyaman.libraryapp.dto.ChangePasswordRequest;
import com.aliyaman.libraryapp.dto.UpdateUserRequest;
import com.aliyaman.libraryapp.dto.UserDto;

import java.util.List;

public interface IUserService {

    public UserDto findUserById(Long id);

    public boolean deleteUser(Long id);

    public List<UserDto> getAllUsers();

    public String assignRoleToUser(AssignRoleRequest request);

    public UserDto updateUser(Long id, UpdateUserRequest request);

    UserDto updateMe(Long currentUserId, UpdateUserRequest request);
    String changeMyPassword(Long currentUserId, ChangePasswordRequest request);


}
