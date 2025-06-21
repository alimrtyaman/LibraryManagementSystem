package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.AssignRoleRequest;
import com.aliyaman.libraryapp.dto.UserDto;

import java.util.List;

public interface IRestUserController {

    public UserDto findUserById(Long id);

    public boolean deleteUser(Long id);

    public List<UserDto> getAllUsers();

    public String assignRoleToUser(AssignRoleRequest request);
}
