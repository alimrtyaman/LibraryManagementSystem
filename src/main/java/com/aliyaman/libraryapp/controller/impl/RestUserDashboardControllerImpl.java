package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestUserDashboardController;
import com.aliyaman.libraryapp.dto.UserDashboardResponse;
import com.aliyaman.libraryapp.service.IUserDashboardService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class RestUserDashboardControllerImpl implements IRestUserDashboardController {

    private final IUserDashboardService dashboardService;

    public RestUserDashboardControllerImpl(IUserDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public UserDashboardResponse dashboard(Authentication authentication) {
        return dashboardService.getDashboard(authentication);
    }


}
