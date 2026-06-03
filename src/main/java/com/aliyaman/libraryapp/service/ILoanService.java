package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.LoanDto;
import com.aliyaman.libraryapp.repository.BookRepository;
import com.aliyaman.libraryapp.repository.UserRepository;

import java.util.List;

public interface ILoanService {

    public LoanDto createLoan(Long userId , Long bookId);


    public List<LoanDto> getAllLoans();

    public boolean deleteLoan(Long loanId);

    public LoanDto markReturned(Long loanId);

}
