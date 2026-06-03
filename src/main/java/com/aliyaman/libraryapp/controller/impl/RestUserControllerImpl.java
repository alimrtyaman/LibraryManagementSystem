package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestUserController;
import com.aliyaman.libraryapp.dto.AssignRoleRequest;
import com.aliyaman.libraryapp.dto.ChangePasswordRequest;
import com.aliyaman.libraryapp.dto.UpdateUserRequest;
import com.aliyaman.libraryapp.dto.UserDto;
import com.aliyaman.libraryapp.entity.CustomUserDetails;
import com.aliyaman.libraryapp.mapper.UserMapper;
import com.aliyaman.libraryapp.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class RestUserControllerImpl implements IRestUserController {

    private IUserService iUserService;
    private  UserMapper userMapper;

    public RestUserControllerImpl(IUserService theiUserService , UserMapper theUserMapper){
        this.iUserService=theiUserService;
        this.userMapper=theUserMapper;
    }

    @GetMapping("/{id}")
    @Override
    public UserDto findUserById(@PathVariable(name = "id") Long id) {
        return iUserService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean deleteUser(@PathVariable Long id) {
        return iUserService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Override
    public List<UserDto> getAllUsers() {
        return iUserService.getAllUsers();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign-role")
    @Override
    public String assignRoleToUser(@RequestBody AssignRoleRequest request) {
        return iUserService.assignRoleToUser(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Override
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody UpdateUserRequest request) {
        return iUserService.updateUser(id, request);
    }

    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userMapper.toDto(userDetails.getUser());
    }

    @PutMapping("/me")
    public UserDto updateMe(@RequestBody UpdateUserRequest request,
                            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getUser().getId();
        return iUserService.updateMe(currentUserId, request);
    }

    @PutMapping("/me/password")
    public String changeMyPassword(@RequestBody ChangePasswordRequest request,
                                   Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getUser().getId();
        return iUserService.changeMyPassword(currentUserId, request);
    }

}
