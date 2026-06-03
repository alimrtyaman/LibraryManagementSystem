package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.AssignRoleRequest;
import com.aliyaman.libraryapp.dto.ChangePasswordRequest;
import com.aliyaman.libraryapp.dto.UpdateUserRequest;
import com.aliyaman.libraryapp.dto.UserDto;
import com.aliyaman.libraryapp.entity.Role;
import com.aliyaman.libraryapp.entity.User;
import com.aliyaman.libraryapp.mapper.UserMapper;
import com.aliyaman.libraryapp.repository.RoleRepository;
import com.aliyaman.libraryapp.repository.UserRepository;
import com.aliyaman.libraryapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder  passwordEncoder;




    @Override
    public UserDto findUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()){
            return null;
        }
        User user = optional.get();
        return userMapper.toDto(user);
    }

    public boolean deleteUser(Long id){
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UserDto> getAllUsers() {

      return   userRepository.findAll()
              .stream()
              .map(userMapper::toDto)
              .collect(Collectors.toList());

    }

    @Override
    public String assignRoleToUser(AssignRoleRequest request){
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        boolean alreadyHasRole = user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(role.getName()));

        if (alreadyHasRole) {
            return "User already has role: " + role.getName();
        }

        user.getRoles().add(role);
        userRepository.save(user);

        return "Role " + role.getName() + " added to user " + user.getEmail();
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        // null olmayanları güncelle (partial update)
        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getSurName() != null) {
            user.setSurName(request.getSurName());
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getCountry() != null) {
            user.setCountry(request.getCountry());
        }

        User saved = userRepository.save(user);

        return userMapper.toDto(saved);
    }

    @Override
    public UserDto updateMe(Long currentUserId, UpdateUserRequest request) {

        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + currentUserId));

        if (request.getName() != null) user.setName(request.getName());
        if (request.getSurName() != null) user.setSurName(request.getSurName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getCountry() != null) user.setCountry(request.getCountry());

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public String changeMyPassword(Long currentUserId, ChangePasswordRequest request) {

        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + currentUserId));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is wrong");
        }

        if (request.getNewPassword() == null || request.getNewPassword().length() < 8) {
            throw new IllegalArgumentException("New password must be at least 8 characters");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return "Password changed successfully";
    }

}
