package com.aliyaman.libraryapp.dto;

import com.aliyaman.libraryapp.entity.Book;
import com.aliyaman.libraryapp.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    private Long id;
    private Date borrowDate;
    private User user;
    private Book book;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private boolean isReturned;
    private boolean isOverdue;
}
