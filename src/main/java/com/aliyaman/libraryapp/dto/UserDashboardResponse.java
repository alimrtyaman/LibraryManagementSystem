package com.aliyaman.libraryapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDashboardResponse {

    private long borrowedBooks;
    private long overdueBooks;
    private long totalLoans;

    private List<LoanDto> currentLoans;
}
