package com.aliyaman.libraryapp.controller;

import com.aliyaman.libraryapp.dto.LoanDto;

import java.util.List;

public interface IRestLoanController {

    public LoanDto createLoan(Long userId , Long bookId);

    public List<LoanDto> getAllLoans();

    public boolean deleteLoan(Long loanId);

    public LoanDto markReturned(Long loanId);

}
