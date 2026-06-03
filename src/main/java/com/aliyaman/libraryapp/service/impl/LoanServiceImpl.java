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
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository,
                           UserRepository userRepository,
                           BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    // 🔹 Entity -> DTO mapper
    private LoanDto toDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setBorrowDate(loan.getBorrowDate());
        dto.setDueDate(loan.getDueDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setReturned(loan.isReturned());
        dto.setOverdue(loan.isOverdue());

        if (loan.getUser() != null) {
            dto.setUserId(loan.getUser().getId());
            dto.setUserName(loan.getUser().getName());
            dto.setUserEmail(loan.getUser().getEmail());
        }

        if (loan.getBook() != null) {
            dto.setBookId(loan.getBook().getId());
            dto.setBookTitle(loan.getBook().getTitle());
        }

        return dto;
    }

    @Override
    @Transactional
    public LoanDto createLoan(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not Found"));


        if (book.getStock() == null || book.getStock() <= 0) {
            throw new IllegalStateException("Book is out of stock");
        }

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setReturned(false);
        loan.setBorrowDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(30));
        loan.setReturnDate(null);
        loan.setOverdue(false);


        int newStock = book.getStock() - 1;
        book.setStock(newStock);
        if (newStock <= 0) {
            book.setStatus(false);
        }

        bookRepository.save(book);
        loanRepository.save(loan);
        return toDto(loan);
    }

    @Override
    @Transactional
    public List<LoanDto> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteLoan(Long loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isEmpty()) {
            throw new IllegalArgumentException("Loan not found");
        }


        loanRepository.delete(optionalLoan.get());
        return true;
    }

    @Override
    @Transactional
    public LoanDto markReturned(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (loan.isReturned()) {
            // zaten iade edilmişse mevcut durumu döndürelim
            return toDto(loan);
        }

        // kitap stoğunu geri artır
        Book book = loan.getBook();
        if (book != null) {
            Integer stock = book.getStock();
            if (stock == null) stock = 0;
            stock = stock + 1;
            book.setStock(stock);

            if (stock > 0) {
                book.setStatus(true);
            }
            bookRepository.save(book);
        }

        // loan bilgilerini güncelle
        LocalDateTime now = LocalDateTime.now();
        loan.setReturnDate(now);
        loan.setReturned(true);

        // gecikme kontrolü
        LocalDateTime due = loan.getDueDate();
        if (due != null && now.isAfter(due)) {
            loan.setOverdue(true);
        } else {
            loan.setOverdue(false);
        }

        Loan saved = loanRepository.save(loan);
        return toDto(saved);
    }

    public LoanDto toDtoPublic(Loan loan) {
        return toDto(loan);
    }

}



