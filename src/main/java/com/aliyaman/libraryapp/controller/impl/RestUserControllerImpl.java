package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestUserController;
import com.aliyaman.libraryapp.dto.UserDto;
import com.aliyaman.libraryapp.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class RestUserControllerImpl implements IRestUserController {

    private IUserService iUserService;

    public RestUserControllerImpl(IUserService theiUserService){
        this.iUserService=theiUserService;
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

    @GetMapping
    @Override
    public List<UserDto> getAllUsers() {
        return iUserService.getAllUsers();
    }
}
