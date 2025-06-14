package com.aliyaman.libraryapp.mapper;

import com.aliyaman.libraryapp.dto.UserDto;
import com.aliyaman.libraryapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);
}
