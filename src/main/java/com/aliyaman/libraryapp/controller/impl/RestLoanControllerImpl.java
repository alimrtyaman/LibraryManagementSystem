package com.aliyaman.libraryapp.controller.impl;

import com.aliyaman.libraryapp.controller.IRestLoanController;
import com.aliyaman.libraryapp.dto.LoanDto;
import com.aliyaman.libraryapp.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
public class RestLoanControllerImpl implements IRestLoanController {

    private final ILoanService loanService;

    @Autowired
    public RestLoanControllerImpl(ILoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loans/{bookId}")
    @Override
    public LoanDto createLoan(@AuthenticationPrincipal(expression = "userId") Long userId,
                              @PathVariable(value = "id") Long bookId) {
        return loanService.createLoan(userId, bookId);
    }
}
