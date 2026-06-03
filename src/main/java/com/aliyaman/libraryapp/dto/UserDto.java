package com.aliyaman.libraryapp.dto;

import com.aliyaman.libraryapp.entity.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String surName;
    private String country;

    private List<Role> roles;


}
