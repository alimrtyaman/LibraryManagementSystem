package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.LoanDto;

public interface IRestLoanController {

    public LoanDto createLoan(Long userId , Long bookId);
}
