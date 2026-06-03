package com.aliyaman.libraryapp.service.impl;

import com.aliyaman.libraryapp.dto.UserDashboardResponse;
import com.aliyaman.libraryapp.entity.CustomUserDetails;
import com.aliyaman.libraryapp.repository.LoanRepository;
import com.aliyaman.libraryapp.service.IUserDashboardService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class UserDashboardServiceImpl implements IUserDashboardService {

    private final LoanRepository loanRepository;
    private final LoanServiceImpl loanService; // mapper için

    public UserDashboardServiceImpl(LoanRepository loanRepository, LoanServiceImpl loanService) {
        this.loanRepository = loanRepository;
        this.loanService = loanService;
    }

    @Override
    public UserDashboardResponse getDashboard(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();

        UserDashboardResponse r = new UserDashboardResponse();

        // cards
        r.setTotalLoans(loanRepository.countByUserId(userId));
        r.setBorrowedBooks(
                loanRepository.countByUserIdAndIsReturnedFalse(userId)
        );
        r.setOverdueBooks(
                loanRepository.countByUserIdAndIsReturnedFalseAndIsOverdueTrue(userId)
        );

        // table: current loans
        r.setCurrentLoans(
                loanRepository
                        .findTop5ByUserIdAndIsReturnedFalseOrderByDueDateAsc(userId)
                        .stream()
                        .map(loanService::toDtoPublic) // aşağıda
                        .toList()
        );

        return r;
    }

}
