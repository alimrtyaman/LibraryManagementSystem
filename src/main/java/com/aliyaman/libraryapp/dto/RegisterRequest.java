package com.aliyaman.libraryapp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "name should not be empty")
    private String name;

    @Email
    private String email;

    @NotEmpty(message = "surname should not be empty")
    private String surName;

    @NotEmpty(message = "password should be empty")
    @Size(min = 6 , max = 20 , message = "password lenght must be between 6-20 chracters")
    private String password;

    private String country;

}
