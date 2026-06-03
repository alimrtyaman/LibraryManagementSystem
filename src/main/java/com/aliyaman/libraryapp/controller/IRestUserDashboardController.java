package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.UserDashboardResponse;
import org.springframework.security.core.Authentication;

public interface IRestUserDashboardController {

    UserDashboardResponse dashboard(Authentication authentication);
}
