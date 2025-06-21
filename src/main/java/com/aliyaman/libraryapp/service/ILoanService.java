package com.aliyaman.libraryapp.service;

import com.aliyaman.libraryapp.dto.LoanDto;
import com.aliyaman.libraryapp.repository.BookRepository;
import com.aliyaman.libraryapp.repository.UserRepository;

public interface ILoanService {

    public LoanDto createLoan(Long userId , Long bookId);

}
