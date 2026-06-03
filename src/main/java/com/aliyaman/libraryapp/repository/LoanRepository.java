package com.aliyaman.libraryapp.repository;

import com.aliyaman.libraryapp.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {


    long countByIsReturnedFalse();

    long countByDueDateBeforeAndIsReturnedFalse(LocalDateTime date);

    long countByUserId(Long userId);
    long countByUserIdAndIsReturnedFalse(Long userId);
    long countByUserIdAndIsReturnedFalseAndIsOverdueTrue(Long userId);
    List<Loan> findByUserIdOrderByDueDateAsc(Long userId);

    List<Loan> findTop5ByUserIdAndIsReturnedFalseOrderByDueDateAsc(Long userId);


}
