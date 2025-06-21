package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.LoanDto;
import com.aliyaman.libraryapp.entity.Book;
import com.aliyaman.libraryapp.entity.Loan;
import com.aliyaman.libraryapp.entity.User;
import com.aliyaman.libraryapp.repository.BookRepository;
import com.aliyaman.libraryapp.repository.LoanRepository;
import com.aliyaman.libraryapp.repository.UserRepository;
import com.aliyaman.libraryapp.service.ILoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    @Transactional
    public LoanDto createLoan(Long userId , Long bookId){
        Loan loan = new Loan();

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found")
        );
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("Book not Found")
        );
        loan.setBook(book);
        loan.setUser(user);
        loan.setReturned(false);
        loan.setBorrowDate(LocalDateTime.now());
        loan.setOverdue(false);
        loan.setReturnDate(LocalDateTime.now().plusDays(30));
        loanRepository.save(loan);
        int newStock = book.getStock() - 1;
        if (book.getStock() <= 0){
            book.setStatus(false);
        }
        bookRepository.save(book);
        LoanDto dto = new LoanDto();
        BeanUtils.copyProperties(loan , dto);
        return dto;
    }

}
