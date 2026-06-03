package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.UserDashboardResponse;
import org.springframework.security.core.Authentication;

public interface IUserDashboardService {

    UserDashboardResponse getDashboard(Authentication authentication);
}
