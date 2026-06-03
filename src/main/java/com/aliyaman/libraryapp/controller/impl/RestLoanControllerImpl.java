package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestLoanController;
import com.aliyaman.libraryapp.dto.LoanDto;
import com.aliyaman.libraryapp.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class RestLoanControllerImpl implements IRestLoanController {

    private final ILoanService loanService;

    @Autowired
    public RestLoanControllerImpl(ILoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @Override
    public LoanDto createLoan(@RequestParam Long userId,
                              @RequestParam Long bookId) {
        return loanService.createLoan(userId, bookId);
    }

    @GetMapping
    @Override
    public List<LoanDto> getAllLoans() {
        return loanService.getAllLoans();
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean deleteLoan(@PathVariable("id") Long loanId) {
        return loanService.deleteLoan(loanId);
    }

    @PatchMapping("/{id}/return")
    @Override
    public LoanDto markReturned(@PathVariable("id") Long loanId) {
        return loanService.markReturned(loanId);
    }
}
