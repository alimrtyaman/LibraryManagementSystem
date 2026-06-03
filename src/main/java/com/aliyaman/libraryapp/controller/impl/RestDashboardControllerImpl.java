package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestDashboardController;
import com.aliyaman.libraryapp.dto.DashboardResponse;
import com.aliyaman.libraryapp.service.IDashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class RestDashboardControllerImpl implements IRestDashboardController {

    private final IDashboardService dashboardService;

    public RestDashboardControllerImpl(IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public DashboardResponse getAdminDashboard() {
        return dashboardService.getAdminDashboard();
    }

}
